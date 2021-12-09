using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Windows.Forms;
using ISO15693DLL;
using System.Threading;
using StackExchange.Redis;
using Newtonsoft.Json;

namespace ISO15693_Reader
{
    public partial class Main : Form
    {
        //串口操作类的实例化
        ISO15693Reader Reader = new ISO15693Reader();
        //IsCommand用来标记当前五个实验中，使用的是那种命令，下面有对应的注释
        private Int32 IsCommand = 0;
        //redis连接
        ConnectionMultiplexer redis = ConnectionMultiplexer.Connect("192.168.10.200:6379");
        public Main()
        {

            InitializeComponent();
        }

        private void Main_Load(object sender, EventArgs e)
        {
            //窗体加载时调用
            RefreshPort();
            cmb_Baud.SelectedIndex = 0;
            cmb_Reset.SelectedIndex = 0;
            group_Operate.Enabled = false;
            group_Paramter.Enabled = false;
            btn_Close.Enabled = false;
            txt_BlockCount.ReadOnly = true;
        }
        /// <summary>
        /// 声明一个委托，用于显示窗体上的List_Info控件的信息
        /// </summary>
        /// <param name="msg"></param>
        private delegate void AddList(string msg);
        /// <summary>
        /// 显示List_Info控件信息的方法
        /// </summary>
        /// <param name="msg"></param>
        private void ShowList(string msg)
        {
            //使用委托显示信息，可避免跨线程调用的问题
            if (list_Info.InvokeRequired)
            {
                AddList d = new AddList(ShowList);
                list_Info.Invoke(d, msg);
            }
            else
            {
                list_Info.Items.Insert(0, DateTime.Now.ToLongTimeString() + "：" + msg);
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

        //打开串口
        private void btn_Open_Click(object sender, EventArgs e)
        {
            if (cmb_Port.SelectedIndex < 0)
            {
                ShowList("打开失败，请选择串口！");
                return;
            }
            Byte value = Reader.OpenSerialPort(cmb_Port.Text, Convert.ToInt32(cmb_Baud.Text));
            if (value == 0x00)
            {
                group_Paramter.Enabled = true;
                group_Operate.Enabled = true;
                btn_Open.Enabled = false;
                btn_Close.Enabled = true;
                ShowList(string.Format("Port:[{0}],OpenSucceed!，Baud:[{1}]", cmb_Port.Text, cmb_Baud.Text));
            }
            else
            {
                ShowList(string.Format("Port[{0}],OpenFail！", cmb_Port.Text));
            }
        }
        //刷新串口
        private void btn_Refresh_Click(object sender, EventArgs e)
        {
            RefreshPort();
        }
        /// <summary>
        /// 刷新串口的方法
        /// </summary>
        private void RefreshPort()
        {
            cmb_Port.Items.Clear();
            cmb_Port.Text = "";
            string[] Ports = System.IO.Ports.SerialPort.GetPortNames();
            foreach (var item in Ports)
            {
                cmb_Port.Items.Add(item);
            }
            if (Ports.Length >= 0)
            {
                cmb_Port.SelectedIndex = 0;
            }
        }
        //关闭串口
        private void btn_Close_Click(object sender, EventArgs e)
        {
            Byte value = Reader.CloseSerialPort();
            if (value == 0x00)
            {
                ShowList(string.Format("Port[{0}],CloseSucceed！", cmb_Port.Text));
            }
            else
            {
                ShowList(string.Format("Port[{0}],CloseFail！", cmb_Port.Text));
            }
        }
        //寻到的卡片数目
        private Int32 TagCount = 0;
        //寻到的卡片号
        private String[] TagNumber = null;
        //声明一个卡片集合用于存放不同卡号的卡片数据
        private List<string> _listTag = new List<string>();
        //执行方法，所有指令都通过这个按钮来控制
        private void btn_Run_Click(object sender, EventArgs e)
        {
            Byte value;
            Byte[] BlockData = null;
            //判断是否打开串口
            if (Reader.IsOpen)
            {
                //调用一个Switch来对每一种选中命令执行相对应的方法
                switch (IsCommand)
                {

                    //寻单卡
                    case 0:
                        if (rb_ask.Checked)
                        {
                            value = Reader.Inventory(ModulateMethod.ASK, InventoryModel.Single, ref TagCount, ref TagNumber);
                            if (value == 0x00)
                            {

                                ShowList(String.Format("Modulate.ASK,TagCount:{0}，TagNumber：{1}", TagCount, TagNumber[0]));
                                if (!_listTag.Contains(TagNumber[0]))
                                {
                                    _listTag.Add(TagNumber[0]);
                                    cmb_tags.Items.Add(TagNumber[0]);
                                    cmb_tags.SelectedIndex = 0;

                                }

                            }
                            else
                            {
                                ShowList("Inventory Lose!No TagNumber");
                            }
                        }
                        else if (rb_fsk.Checked)
                        {
                            value = Reader.Inventory(ModulateMethod.FSK, InventoryModel.Single, ref TagCount, ref TagNumber);
                            if (value == 0x00)
                            {
                                if (!_listTag.Contains(TagNumber[0]))
                                {
                                    _listTag.Add(TagNumber[0]);
                                    cmb_tags.Items.Add(TagNumber[0]);
                                    cmb_tags.SelectedIndex = 0;
                                }
                                ShowList(String.Format("Modulate.FSK,TagCount:{0}，TagNumber：{1}", TagCount, TagNumber[0]));
                            }
                            else
                            {
                                ShowList("Inventory Lose!No TagNumber");
                            }

                        }
                        else
                        {

                        }
                        break;
                    //读取指定的数据块中的数据
                    case 1:
                        if (cmb_tags.Text != "")
                        {
                            value = Reader.ReadSingleBlock(cmb_tags.Text, BlockLength.ShortBlock4Byte, Convert.ToByte(txt_StartAdress.Text.Trim(), 16), ref BlockData);
                            if (value == 0x00)
                            {
                                StringBuilder sb = new StringBuilder();
                                for (int i = 0; i < BlockData.Length; i++)
                                {
                                    sb.Append(BlockData[i].ToString("X2"));
                                }
                                txt_Resulte.Text = sb.ToString();
                                txt_WriteData.Text = txt_Resulte.Text;
                                ShowList(string.Format("Reader:[{0}],Adress:[{1}]，ReaderData：{2}", cmb_tags.Text, txt_StartAdress.Text.Trim(), ByteArrayToString(BlockData)));
                            }
                        }
                        else
                        {
                            ShowList("请先寻到一张或多张卡片！");
                        }
                        break;
                    //写入数据到卡片中指定的数据块
                    case 2:
                        // Byte BlockLength = Byte.Parse(txt);
                        if (cmb_tags.Text != "")
                        {
                            Byte[] DataForWrite = new Byte[Convert.ToInt32(txt_BlockSize.Text.Trim())];
                            String strForWrite = txt_WriteData.Text.Trim();
                            try
                            {
                                for (Byte i = 0; i < DataForWrite.Length; i++)
                                {
                                    DataForWrite[i] = Convert.ToByte(strForWrite.Substring(i * 2, 2), 16);
                                }
                            }
                            catch (System.ArgumentOutOfRangeException ex)
                            {
                                ShowList(String.Format("错误：写入的数据长度不够{0}Byte！", Convert.ToInt32(txt_BlockSize.Text.Trim())));
                                //txt_WriteData_2.SelectAll();
                                //txt_WriteData_2.Focus();
                                return;
                            }
                            catch (System.Exception ex)
                            {
                                ShowList(String.Format("错误：请填写{0}Byte的16进制数据！", Convert.ToInt32(txt_BlockSize.Text.Trim())));
                                txt_WriteData.SelectAll();
                                txt_WriteData.Focus();
                                return;
                            }
                            value = Reader.WriteSingleBlock(cmb_tags.Text, BlockLength.ShortBlock4Byte, Convert.ToByte(txt_StartAdress.Text.Trim(), 16), DataForWrite);
                            if (value == 0x00)
                            {
                                ShowList(string.Format("Succeed!,Adress:[{0}],WriteData：{1}", txt_StartAdress.Text, txt_WriteData.Text));
                            }
                            else
                            {
                                ShowList(string.Format("Lose!Adress：[{0}],WriteData：{1}", txt_StartAdress.Text, txt_WriteData.Text));
                            }
                        }
                        else
                        {
                            ShowList("请先寻到一张或多张卡片！");
                        }
                        break;
                    //使卡片静默
                    case 3:
                        if (cmb_tags.Text != "")
                        {
                            value = Reader.StayQuiet(cmb_tags.Text);
                            if (value == 0x00)
                            {
                                ShowList(string.Format("命令执行成功,卡片[{0}]静默成功！", cmb_tags.Text));
                            }
                            else
                            {
                                ShowList(string.Format("命令执行失败,卡片[{0}]静默失败！", cmb_tags.Text));
                            }
                        }
                        break;
                    //重置卡片状态
                    case 4:
                        if (cmb_Reset.SelectedIndex != 0)
                        {
                            //ResetMode resetMode;
                            switch (cmb_Reset.SelectedIndex)
                            {
                                case 1:
                                    // resetMode=ResetMode.RstAllQuiet;
                                    value = Reader.ResetToReady(ResetMode.RstAllQuiet);
                                    break;
                                case 2:
                                    //resetMode=ResetMode.RstAllSelected;
                                    value = Reader.ResetToReady(ResetMode.RstAllSelected);
                                    break;
                                case 3:
                                    // resetMode=ResetMode.RstSpecificQuiet;
                                    value = Reader.ResetToReady(ResetMode.RstSpecificQuiet, cmb_tags.Text);
                                    break;
                                case 4:
                                    // resetMode=ResetMode.RstSpecificSelected;
                                    value = Reader.ResetToReady(ResetMode.RstSpecificSelected, cmb_tags.Text);
                                    break;
                                default:
                                    //resetMode=ResetMode.RstSpecificQuiet;
                                    value = Reader.ResetToReady(ResetMode.RstAllQuiet);
                                    break;
                            }



                            if (value == 0x00)
                            {
                                switch (cmb_Reset.SelectedIndex)
                                {
                                    case 1:
                                        ShowList("重置所有静默卡片命令执行成功！");
                                        break;
                                    case 2:
                                        ShowList("重置所有选中卡片命令执行成功！");
                                        break;
                                    case 3:
                                        ShowList(string.Format("重置静默卡片[{0}]执行成功！", cmb_tags.Text));
                                        break;
                                    case 4:
                                        ShowList(string.Format("重置选中卡片[{0}]执行成功！", cmb_tags.Text));
                                        break;
                                    default:
                                        //resetMode = ResetMode.RstSpecificQuiet;
                                        break;
                                }
                            }
                            else
                            {
                                switch (cmb_Reset.SelectedIndex)
                                {
                                    case 1:
                                        ShowList("重置所有静默卡片命令执行失败！");
                                        break;
                                    case 2:
                                        ShowList("重置所有选中卡片命令执行失败！");
                                        break;
                                    case 3:
                                        ShowList(string.Format("重置静默卡片[{0}]执行失败！", cmb_tags.Text));
                                        break;
                                    case 4:
                                        ShowList(string.Format("重置选中卡片[{0}]执行失败！", cmb_tags.Text));
                                        break;
                                    default:
                                        //resetMode = ResetMode.RstSpecificQuiet;
                                        break;
                                }
                            }
                        }
                        else
                        {
                            ShowList("请选择一种静默方式才可执行！");
                        }
                        break;
                    //读取多个数据块
                    case 5:
                        if (cmb_tags.Text != "")
                        {
                            string str = "";
                            Byte[] BlockDataMore = null;
                            int count = 0;
                            if (int.TryParse(txt_BlockCount.Text.Trim(), out count))
                            {
                                int Address = 0;
                                if (int.TryParse(txt_StartAdress.Text.Trim(), out Address))
                                {
                                    value = Reader.ReadMultiBlock(cmb_tags.Text, BlockLength.ShortBlock4Byte, (byte)Address, (byte)count, ref BlockDataMore);
                                    if (value == 0x00)
                                    {
                                        StringBuilder sb = new StringBuilder();
                                        for (int i = 0; i < BlockDataMore.Length; i++)
                                        {
                                            sb.Append(BlockDataMore[i].ToString("X2"));
                                        }
                                        for (int i = 0; i < count; i++)
                                        {
                                            str += sb.ToString().Substring(i * 4, 8)+" ";
                                        }
                                        txt_Resulte.Text = str;
                                        txt_WriteData.Text = sb.ToString();
                                        ShowList(string.Format("Reader[{0}],Aderess:{1}，ReaderCount:{3},Data：{2}", cmb_tags.Text, txt_StartAdress.Text.Trim(), ByteArrayToString(BlockDataMore), count));
                                    }
                                }
                            }
                            else
                            {
                                MessageBox.Show("请填写正确的读取数据的大小");
                            }
                        }
                        break;
                    
                    default:

                        break;
                }

            }
            else
            {
                ShowList("串口未打开，请打开串口！");
            }
        }

        private void rb_Inventory_CheckedChanged(object sender, EventArgs e)
        {
            //寻单卡
            if (rb_Inventory.Checked)
            {
                btn_AutoRun.Enabled = true;
                IsCommand = 0;
            }
            else
            {
                btn_AutoRun.Enabled = false;
            }
        }

        private void rb_ReadSingle_CheckedChanged(object sender, EventArgs e)
        {
            //读取指定的数据块中的数据
            if (rb_ReadSingle.Checked)
            {
                IsCommand = 1;
                txt_BlockCount.ReadOnly = true;
            }
        }

        private void rb_WriteSingle_CheckedChanged(object sender, EventArgs e)
        {
            //写入数据到卡片中指定的数据块
            if (rb_WriteSingle.Checked)
            {
                IsCommand = 2;
            }
        }

        private void rb_StayQuiet_CheckedChanged(object sender, EventArgs e)
        {
            //使卡片静默
            if (rb_StayQuiet.Checked)
            {
                IsCommand = 3;
            }
        }

        private void rb_ResetReady_CheckedChanged(object sender, EventArgs e)
        {
            //重置卡片状态
            if (rb_ResetReady.Checked)
            {
                IsCommand = 4;
            }
        }

        private void rb_ReadMultiple_CheckedChanged(object sender, EventArgs e)
        {
            //读取多个数据块
            if (rb_ReadMultiple.Checked)
            {
                IsCommand = 5;
                txt_BlockCount.ReadOnly = false;
            }
        }

        private void rb_AutoInvertory_CheckedChanged(object sender, EventArgs e)
        {
            //寻多卡
            if (rb_AutoInvertory.Checked)
            {
                btn_AutoRun.Enabled = true;
                IsCommand = 6;
            }
            else
            {
                btn_AutoRun.Enabled = false;
            }
        }
        /// <summary>
        /// 将字节数组转换为十六进制的字符串
        /// </summary>
        /// <param name="array">字节数组</param>
        /// <returns>字符串</returns>
        private String ByteArrayToString(Byte[] array)
        {
            StringBuilder sb = new StringBuilder();
            foreach (Byte a in array) { sb = sb.Append(a.ToString("X2")); }
            return sb.ToString();
        }
        private String ByteArrayToString(Byte[] array, Int32 StartPos, Int32 Length)
        {
            StringBuilder sb = new StringBuilder();
            for (Int32 index = StartPos; index < StartPos + Length; index++)
            { sb = sb.Append(array[index].ToString("X2")); }
            return sb.ToString();
        }
        /// <summary>
        /// 将十六进制的字符串转换为字节数组
        /// </summary>
        /// <param name="str">字符串</param>
        /// <returns>字节数组</returns>
        private Byte[] StringToByteArray(String str)
        {
            Byte[] data = new Byte[str.Length / 2];
            for (Int32 i = 0; i < data.Length; i++)
            {
                data[i] = Convert.ToByte(str.Substring(i * 2, 2), 16);
            }
            return data;
        }
        //当选择重置方式的值发生更改时，对应的命令也会变为重置模式
        private void cmb_Reset_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (cmb_Reset.SelectedIndex != 0)
            {
                rb_ResetReady.Checked = true;
            }
        }

        private void btn_CloseSystem_Click(object sender, EventArgs e)
        {
            if (MessageBox.Show("是否退出系统？", "提示", MessageBoxButtons.OKCancel, MessageBoxIcon.Question) == System.Windows.Forms.DialogResult.OK)
            {
                this.Close();
            }
        }
        //清空文本框
        private void btn_Clear_Click(object sender, EventArgs e)
        {
            txt_Resulte.Text = "";
            txt_WriteData.Text = "";
            list_Info.Items.Clear();

        }
        //循环寻多卡
        private void btn_AutoRun_Click(object sender, EventArgs e)
        {
            //开启寻多卡的线程
            Thread th = new Thread(AutoRun);
            th.IsBackground = true;
            if (btn_AutoRun.Text == "AutoRun")
            {
                IsStop = true;
                btn_AutoRun.Text = "StopRun";
                th.Start();
            }
            else
            {
                IsStop = false;
                btn_AutoRun.Text = "AutoRun";
            }
        }
        //添加标签的委托
        private delegate void AddItem(string tag);
        private void AddCmbTagNumber(string tag)
        {
            if (cmb_tags.InvokeRequired)
            {
                AddItem d = new AddItem(AddCmbTagNumber);
                cmb_tags.Invoke(d, tag);
            }
            else
            {
                cmb_tags.Items.Add(tag);
                cmb_tags.SelectedIndex = 0;
            }
        }
        //控制寻多卡循环是否继续进行
        private Boolean IsStop = false;
        //寻多卡的循环方法
        private void AutoRun()
        {
            Byte value;
            while (IsStop)
            {
                if (rb_ask.Checked)
                {
                    value = Reader.Inventory(ModulateMethod.ASK, InventoryModel.Multiple, ref TagCount, ref TagNumber);
                    if (value == 0x00)
                    {
                        for (int i = 0; i < TagNumber.Length; i++)
                        {
                            ShowList(String.Format("Modulate.ASK,TagCount:{0}，TagNumber：{1}", TagCount, TagNumber[i]));
                            if (!_listTag.Contains(TagNumber[i]))
                            {
                                _listTag.Add(TagNumber[i]);
                                AddCmbTagNumber(TagNumber[i]);
                             
                            }
                        }
                    }
                    else
                    {
                        ShowList("Inventory Lose!No TagNumber");
                    }
                }
                else if (rb_fsk.Checked)
                {
                    value = Reader.Inventory(ModulateMethod.FSK, InventoryModel.Multiple, ref TagCount, ref TagNumber);
                    if (value == 0x00)
                    {
                        for (int i = 0; i < TagNumber.Length; i++)
                        {
                            if (!_listTag.Contains(TagNumber[i]))
                            {
                                _listTag.Add(TagNumber[i]);
                                AddCmbTagNumber(TagNumber[i]);
                            }
                            ShowList(String.Format("Modulate.FSK,TagCount:{0}，TagNumber：{1}", TagCount, TagNumber[i]));
                        }
                    }
                    else
                    {
                        ShowList("Inventory Lose!No TagNumber");
                    }

                }
                else
                {

                }
            }
        }

        private void Main_FormClosing(object sender, FormClosingEventArgs e)
        {
            if (Reader.IsOpen)
            {
                if (IsStop)
                {
                    IsStop = false;
                }
                Reader.CloseSerialPort();
            }
        }

        private void group_Paramter_Enter(object sender, EventArgs e)
        {

        }


   
    }
}
