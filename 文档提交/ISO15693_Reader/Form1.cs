using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using StackExchange.Redis;
using Newtonsoft.Json;
using ISO15693DLL;

namespace ISO15693_Reader
{
    public partial class Form1 : Form
    {
        private int TagCount;
        string[] TagNumber;
        private string GetNumber = "";
        //public string PortName = ConfigurationManager.AppSettings["Portname"];
        //public int bandrate = Convert.ToInt32(ConfigurationManager.AppSettings["bandrate"]);

        public string PortName = "COM3";
        public int bandrate = 115200;

        ConnectionMultiplexer redis = ConnectionMultiplexer.Connect("192.168.10.200:6379");
        //串口操作类的实例化
        ISO15693Reader Reader = new ISO15693Reader();

        private void testredis()
        {
            IDatabase db = redis.GetDatabase();
            //设置string类型的key的值
            db.StringSet("Name", "abc");
            //设置string类型的key的值 带过期时间
            db.StringSet("Id", "123", TimeSpan.FromSeconds(3));
            //以KeyValuePair数组形式批量写入
            var keyvp1 = new KeyValuePair<RedisKey, RedisValue>("name1", "Jhon");
            var keyvp2 = new KeyValuePair<RedisKey, RedisValue>("name2", "Lilei");
            var keyvp3 = new KeyValuePair<RedisKey, RedisValue>("name3", "Jim");
            KeyValuePair<RedisKey, RedisValue>[] values = { keyvp1, keyvp2, keyvp3 };
            db.StringSet(values);
        }
        private void getData()
        {
            IDatabase db = redis.GetDatabase();
            //--------- 读取方法操作-------------------------
            //读取单个key的值
            string Name = db.StringGet("Name");
            string Id = db.StringGet("Id");
            //批量读取key的值
            RedisKey[] rkarray = { "name1", "name2", "name3" };
            RedisValue[] rvarray = db.StringGet(rkarray);
            foreach (var item in rvarray)
            {
                MessageBox.Show(item);
            }

        }

        //监听队列
        private Boolean getOrder()
        {
            IDatabase db = redis.GetDatabase();
            while (true)
            {
                long cout = db.ListLength("orderList");
                if (cout > 0)
                {
                    //清空队列
                    db.ListTrim("orderList", 1, 0);
                    break;
                }
            }
            return true;
        }

        //存放rfid
        private void setRfid(String rfid)
        {
            IDatabase db = redis.GetDatabase();
            Dictionary<string, string> data = new Dictionary<string, string>();
            data.Add("RFID", rfid);
            string s = JsonConvert.SerializeObject(data);
            TimeSpan ts = DateTime.UtcNow - new DateTime(1970, 1, 1, 0, 0, 0, 0);
            double time = double.Parse(Convert.ToInt64(ts.TotalMilliseconds).ToString());
            db.SortedSetAdd("rfidList", s, time);

        }

        public Form1()
        {
            InitializeComponent();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            //打开串口
            

        }

        private void button2_Click(object sender, EventArgs e)
        {


            if (!Reader.IsOpen)
            {
                if (Reader.OpenSerialPort(PortName, bandrate) != 0x00)
                {
                    MessageBox.Show("错误，串口打开失败！");
                    return;
                }
            }
            while (true)
            {
                //读取数据
                byte value = Reader.Inventory(ModulateMethod.ASK, InventoryModel.Single, ref TagCount, ref TagNumber);
                if (value == 0x00)
                {
                    if (TagCount == 1)
                    {
                        string rfid = TagNumber[0];
                        while (true)
                        {
                            //监听队列
                            if (getOrder())
                            {
                                //获取数据,通过串口
                                //MessageBox.Show(rfid);
                                //发送数据
                                setRfid(rfid);
                                //成功读取一次
                                break;
                            }
                        }
                        //break;
                    }
                    else
                    {
                        MessageBox.Show("读卡失败，一次只能读取一张卡！");
                        return;
                    }
                }
                else
                {
                    MessageBox.Show("读卡失败，当前没有读到卡！");
                }
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            Byte value1 = Reader.OpenSerialPort("COM3", Convert.ToInt32("115200"));
            if (value1 == 0x00)
            {
                //开启串口成功
                MessageBox.Show("开启端口成功");
            }
        }
    }
}
