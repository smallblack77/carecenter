namespace ISO15693_Reader
{
    partial class Main
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.group_Port = new System.Windows.Forms.GroupBox();
            this.btn_Refresh = new System.Windows.Forms.Button();
            this.btn_Close = new System.Windows.Forms.Button();
            this.btn_Open = new System.Windows.Forms.Button();
            this.cmb_Baud = new System.Windows.Forms.ComboBox();
            this.label2 = new System.Windows.Forms.Label();
            this.cmb_Port = new System.Windows.Forms.ComboBox();
            this.label1 = new System.Windows.Forms.Label();
            this.group_Operate = new System.Windows.Forms.GroupBox();
            this.groupBox5 = new System.Windows.Forms.GroupBox();
            this.rb_fsk = new System.Windows.Forms.RadioButton();
            this.rb_ask = new System.Windows.Forms.RadioButton();
            this.rb_AutoInvertory = new System.Windows.Forms.RadioButton();
            this.cmb_Reset = new System.Windows.Forms.ComboBox();
            this.label8 = new System.Windows.Forms.Label();
            this.cmb_tags = new System.Windows.Forms.ComboBox();
            this.label3 = new System.Windows.Forms.Label();
            this.btn_AutoRun = new System.Windows.Forms.Button();
            this.btn_Run = new System.Windows.Forms.Button();
            this.rb_ReadMultiple = new System.Windows.Forms.RadioButton();
            this.rb_ResetReady = new System.Windows.Forms.RadioButton();
            this.rb_StayQuiet = new System.Windows.Forms.RadioButton();
            this.rb_WriteSingle = new System.Windows.Forms.RadioButton();
            this.rb_ReadSingle = new System.Windows.Forms.RadioButton();
            this.rb_Inventory = new System.Windows.Forms.RadioButton();
            this.label4 = new System.Windows.Forms.Label();
            this.txt_StartAdress = new System.Windows.Forms.TextBox();
            this.label5 = new System.Windows.Forms.Label();
            this.txt_BlockSize = new System.Windows.Forms.TextBox();
            this.label6 = new System.Windows.Forms.Label();
            this.txt_BlockCount = new System.Windows.Forms.TextBox();
            this.label7 = new System.Windows.Forms.Label();
            this.txt_Resulte = new System.Windows.Forms.TextBox();
            this.label9 = new System.Windows.Forms.Label();
            this.txt_WriteData = new System.Windows.Forms.TextBox();
            this.group_Paramter = new System.Windows.Forms.GroupBox();
            this.list_Info = new System.Windows.Forms.ListBox();
            this.groupBox4 = new System.Windows.Forms.GroupBox();
            this.btn_CloseSystem = new System.Windows.Forms.Button();
            this.btn_Clear = new System.Windows.Forms.Button();
            this.group_Port.SuspendLayout();
            this.group_Operate.SuspendLayout();
            this.groupBox5.SuspendLayout();
            this.group_Paramter.SuspendLayout();
            this.groupBox4.SuspendLayout();
            this.SuspendLayout();
            // 
            // group_Port
            // 
            this.group_Port.Controls.Add(this.btn_Refresh);
            this.group_Port.Controls.Add(this.btn_Close);
            this.group_Port.Controls.Add(this.btn_Open);
            this.group_Port.Controls.Add(this.cmb_Baud);
            this.group_Port.Controls.Add(this.label2);
            this.group_Port.Controls.Add(this.cmb_Port);
            this.group_Port.Controls.Add(this.label1);
            this.group_Port.Location = new System.Drawing.Point(1, 3);
            this.group_Port.Name = "group_Port";
            this.group_Port.Size = new System.Drawing.Size(258, 75);
            this.group_Port.TabIndex = 0;
            this.group_Port.TabStop = false;
            this.group_Port.Text = "端口管理:";
            // 
            // btn_Refresh
            // 
            this.btn_Refresh.Location = new System.Drawing.Point(171, 44);
            this.btn_Refresh.Name = "btn_Refresh";
            this.btn_Refresh.Size = new System.Drawing.Size(75, 23);
            this.btn_Refresh.TabIndex = 2;
            this.btn_Refresh.Text = "刷新";
            this.btn_Refresh.UseVisualStyleBackColor = true;
            this.btn_Refresh.Click += new System.EventHandler(this.btn_Refresh_Click);
            // 
            // btn_Close
            // 
            this.btn_Close.Location = new System.Drawing.Point(78, 44);
            this.btn_Close.Name = "btn_Close";
            this.btn_Close.Size = new System.Drawing.Size(62, 23);
            this.btn_Close.TabIndex = 2;
            this.btn_Close.Text = "关闭";
            this.btn_Close.UseVisualStyleBackColor = true;
            this.btn_Close.Click += new System.EventHandler(this.btn_Close_Click);
            // 
            // btn_Open
            // 
            this.btn_Open.Location = new System.Drawing.Point(9, 44);
            this.btn_Open.Name = "btn_Open";
            this.btn_Open.Size = new System.Drawing.Size(62, 23);
            this.btn_Open.TabIndex = 2;
            this.btn_Open.Text = "打开";
            this.btn_Open.UseVisualStyleBackColor = true;
            this.btn_Open.Click += new System.EventHandler(this.btn_Open_Click);
            // 
            // cmb_Baud
            // 
            this.cmb_Baud.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmb_Baud.FormattingEnabled = true;
            this.cmb_Baud.Items.AddRange(new object[] {
            "115200",
            "57600",
            "9600"});
            this.cmb_Baud.Location = new System.Drawing.Point(163, 18);
            this.cmb_Baud.Name = "cmb_Baud";
            this.cmb_Baud.Size = new System.Drawing.Size(83, 20);
            this.cmb_Baud.TabIndex = 1;
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Location = new System.Drawing.Point(116, 21);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(41, 12);
            this.label2.TabIndex = 0;
            this.label2.Text = "波特：";
            // 
            // cmb_Port
            // 
            this.cmb_Port.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmb_Port.FormattingEnabled = true;
            this.cmb_Port.Location = new System.Drawing.Point(54, 18);
            this.cmb_Port.Name = "cmb_Port";
            this.cmb_Port.Size = new System.Drawing.Size(56, 20);
            this.cmb_Port.TabIndex = 1;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Location = new System.Drawing.Point(7, 21);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(41, 12);
            this.label1.TabIndex = 0;
            this.label1.Text = "端口：";
            // 
            // group_Operate
            // 
            this.group_Operate.Controls.Add(this.groupBox5);
            this.group_Operate.Controls.Add(this.rb_AutoInvertory);
            this.group_Operate.Controls.Add(this.cmb_Reset);
            this.group_Operate.Controls.Add(this.label8);
            this.group_Operate.Controls.Add(this.cmb_tags);
            this.group_Operate.Controls.Add(this.label3);
            this.group_Operate.Controls.Add(this.btn_AutoRun);
            this.group_Operate.Controls.Add(this.btn_Run);
            this.group_Operate.Controls.Add(this.rb_ReadMultiple);
            this.group_Operate.Controls.Add(this.rb_ResetReady);
            this.group_Operate.Controls.Add(this.rb_StayQuiet);
            this.group_Operate.Controls.Add(this.rb_WriteSingle);
            this.group_Operate.Controls.Add(this.rb_ReadSingle);
            this.group_Operate.Controls.Add(this.rb_Inventory);
            this.group_Operate.Location = new System.Drawing.Point(1, 77);
            this.group_Operate.Name = "group_Operate";
            this.group_Operate.Size = new System.Drawing.Size(258, 324);
            this.group_Operate.TabIndex = 1;
            this.group_Operate.TabStop = false;
            this.group_Operate.Text = "命令操作:";
            // 
            // groupBox5
            // 
            this.groupBox5.Controls.Add(this.rb_fsk);
            this.groupBox5.Controls.Add(this.rb_ask);
            this.groupBox5.Location = new System.Drawing.Point(62, 178);
            this.groupBox5.Name = "groupBox5";
            this.groupBox5.Size = new System.Drawing.Size(142, 49);
            this.groupBox5.TabIndex = 8;
            this.groupBox5.TabStop = false;
            this.groupBox5.Text = "调制模式";
            // 
            // rb_fsk
            // 
            this.rb_fsk.AutoSize = true;
            this.rb_fsk.Location = new System.Drawing.Point(84, 20);
            this.rb_fsk.Name = "rb_fsk";
            this.rb_fsk.Size = new System.Drawing.Size(41, 16);
            this.rb_fsk.TabIndex = 0;
            this.rb_fsk.Text = "FSK";
            this.rb_fsk.UseVisualStyleBackColor = true;
            // 
            // rb_ask
            // 
            this.rb_ask.AutoSize = true;
            this.rb_ask.Checked = true;
            this.rb_ask.Location = new System.Drawing.Point(6, 20);
            this.rb_ask.Name = "rb_ask";
            this.rb_ask.Size = new System.Drawing.Size(41, 16);
            this.rb_ask.TabIndex = 0;
            this.rb_ask.TabStop = true;
            this.rb_ask.Text = "ASK";
            this.rb_ask.UseVisualStyleBackColor = true;
            // 
            // rb_AutoInvertory
            // 
            this.rb_AutoInvertory.AutoSize = true;
            this.rb_AutoInvertory.Location = new System.Drawing.Point(118, 32);
            this.rb_AutoInvertory.Name = "rb_AutoInvertory";
            this.rb_AutoInvertory.Size = new System.Drawing.Size(59, 16);
            this.rb_AutoInvertory.TabIndex = 7;
            this.rb_AutoInvertory.TabStop = true;
            this.rb_AutoInvertory.Text = "寻多卡";
            this.rb_AutoInvertory.UseVisualStyleBackColor = true;
            this.rb_AutoInvertory.CheckedChanged += new System.EventHandler(this.rb_AutoInvertory_CheckedChanged);
            // 
            // cmb_Reset
            // 
            this.cmb_Reset.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmb_Reset.FormattingEnabled = true;
            this.cmb_Reset.Items.AddRange(new object[] {
            "请选择一种重置方式",
            "重置所有静默的卡",
            "重置所有选中的卡",
            "重置特定静默的卡",
            "重置特定选中的卡"});
            this.cmb_Reset.Location = new System.Drawing.Point(63, 263);
            this.cmb_Reset.Name = "cmb_Reset";
            this.cmb_Reset.Size = new System.Drawing.Size(141, 20);
            this.cmb_Reset.TabIndex = 1;
            this.cmb_Reset.SelectedIndexChanged += new System.EventHandler(this.cmb_Reset_SelectedIndexChanged);
            // 
            // label8
            // 
            this.label8.AutoSize = true;
            this.label8.Location = new System.Drawing.Point(13, 267);
            this.label8.Name = "label8";
            this.label8.Size = new System.Drawing.Size(65, 12);
            this.label8.TabIndex = 0;
            this.label8.Text = "重置方式：";
            // 
            // cmb_tags
            // 
            this.cmb_tags.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.cmb_tags.FormattingEnabled = true;
            this.cmb_tags.Location = new System.Drawing.Point(63, 233);
            this.cmb_tags.Name = "cmb_tags";
            this.cmb_tags.Size = new System.Drawing.Size(141, 20);
            this.cmb_tags.TabIndex = 1;
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Location = new System.Drawing.Point(13, 237);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(41, 12);
            this.label3.TabIndex = 0;
            this.label3.Text = "标签：";
            // 
            // btn_AutoRun
            // 
            this.btn_AutoRun.ForeColor = System.Drawing.Color.Red;
            this.btn_AutoRun.Location = new System.Drawing.Point(157, 291);
            this.btn_AutoRun.Name = "btn_AutoRun";
            this.btn_AutoRun.Size = new System.Drawing.Size(95, 27);
            this.btn_AutoRun.TabIndex = 6;
            this.btn_AutoRun.Text = "自动运行";
            this.btn_AutoRun.UseVisualStyleBackColor = true;
            this.btn_AutoRun.Click += new System.EventHandler(this.btn_AutoRun_Click);
            // 
            // btn_Run
            // 
            this.btn_Run.Font = new System.Drawing.Font("宋体", 9F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.btn_Run.ForeColor = System.Drawing.Color.Red;
            this.btn_Run.Location = new System.Drawing.Point(11, 291);
            this.btn_Run.Name = "btn_Run";
            this.btn_Run.Size = new System.Drawing.Size(95, 27);
            this.btn_Run.TabIndex = 6;
            this.btn_Run.Text = "运行";
            this.btn_Run.UseVisualStyleBackColor = true;
            this.btn_Run.Click += new System.EventHandler(this.btn_Run_Click);
            // 
            // rb_ReadMultiple
            // 
            this.rb_ReadMultiple.AutoSize = true;
            this.rb_ReadMultiple.Location = new System.Drawing.Point(15, 77);
            this.rb_ReadMultiple.Name = "rb_ReadMultiple";
            this.rb_ReadMultiple.Size = new System.Drawing.Size(107, 16);
            this.rb_ReadMultiple.TabIndex = 5;
            this.rb_ReadMultiple.Text = "读取多个数据块";
            this.rb_ReadMultiple.UseVisualStyleBackColor = true;
            this.rb_ReadMultiple.CheckedChanged += new System.EventHandler(this.rb_ReadMultiple_CheckedChanged);
            // 
            // rb_ResetReady
            // 
            this.rb_ResetReady.AutoSize = true;
            this.rb_ResetReady.Location = new System.Drawing.Point(15, 146);
            this.rb_ResetReady.Name = "rb_ResetReady";
            this.rb_ResetReady.Size = new System.Drawing.Size(95, 16);
            this.rb_ResetReady.TabIndex = 4;
            this.rb_ResetReady.Text = "重置卡片状态";
            this.rb_ResetReady.UseVisualStyleBackColor = true;
            this.rb_ResetReady.CheckedChanged += new System.EventHandler(this.rb_ResetReady_CheckedChanged);
            // 
            // rb_StayQuiet
            // 
            this.rb_StayQuiet.AutoSize = true;
            this.rb_StayQuiet.Location = new System.Drawing.Point(15, 124);
            this.rb_StayQuiet.Name = "rb_StayQuiet";
            this.rb_StayQuiet.Size = new System.Drawing.Size(71, 16);
            this.rb_StayQuiet.TabIndex = 3;
            this.rb_StayQuiet.Text = "静默卡片";
            this.rb_StayQuiet.UseVisualStyleBackColor = true;
            this.rb_StayQuiet.CheckedChanged += new System.EventHandler(this.rb_StayQuiet_CheckedChanged);
            // 
            // rb_WriteSingle
            // 
            this.rb_WriteSingle.AutoSize = true;
            this.rb_WriteSingle.Location = new System.Drawing.Point(15, 100);
            this.rb_WriteSingle.Name = "rb_WriteSingle";
            this.rb_WriteSingle.Size = new System.Drawing.Size(131, 16);
            this.rb_WriteSingle.TabIndex = 2;
            this.rb_WriteSingle.Text = "写入指定数据块数据";
            this.rb_WriteSingle.UseVisualStyleBackColor = true;
            this.rb_WriteSingle.CheckedChanged += new System.EventHandler(this.rb_WriteSingle_CheckedChanged);
            // 
            // rb_ReadSingle
            // 
            this.rb_ReadSingle.AutoSize = true;
            this.rb_ReadSingle.Location = new System.Drawing.Point(15, 54);
            this.rb_ReadSingle.Name = "rb_ReadSingle";
            this.rb_ReadSingle.Size = new System.Drawing.Size(131, 16);
            this.rb_ReadSingle.TabIndex = 1;
            this.rb_ReadSingle.Text = "读取指定数据块数据";
            this.rb_ReadSingle.UseVisualStyleBackColor = true;
            this.rb_ReadSingle.CheckedChanged += new System.EventHandler(this.rb_ReadSingle_CheckedChanged);
            // 
            // rb_Inventory
            // 
            this.rb_Inventory.AutoSize = true;
            this.rb_Inventory.Checked = true;
            this.rb_Inventory.Location = new System.Drawing.Point(15, 31);
            this.rb_Inventory.Name = "rb_Inventory";
            this.rb_Inventory.Size = new System.Drawing.Size(59, 16);
            this.rb_Inventory.TabIndex = 0;
            this.rb_Inventory.TabStop = true;
            this.rb_Inventory.Text = "寻单卡";
            this.rb_Inventory.UseVisualStyleBackColor = true;
            this.rb_Inventory.CheckedChanged += new System.EventHandler(this.rb_Inventory_CheckedChanged);
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Location = new System.Drawing.Point(17, 21);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(59, 12);
            this.label4.TabIndex = 0;
            this.label4.Text = "起始地址:";
            // 
            // txt_StartAdress
            // 
            this.txt_StartAdress.Location = new System.Drawing.Point(96, 17);
            this.txt_StartAdress.Name = "txt_StartAdress";
            this.txt_StartAdress.Size = new System.Drawing.Size(35, 21);
            this.txt_StartAdress.TabIndex = 1;
            this.txt_StartAdress.Text = "0";
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Location = new System.Drawing.Point(34, 47);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(59, 12);
            this.label5.TabIndex = 0;
            this.label5.Text = "卡片大小:";
            // 
            // txt_BlockSize
            // 
            this.txt_BlockSize.Location = new System.Drawing.Point(96, 44);
            this.txt_BlockSize.Name = "txt_BlockSize";
            this.txt_BlockSize.ReadOnly = true;
            this.txt_BlockSize.Size = new System.Drawing.Size(35, 21);
            this.txt_BlockSize.TabIndex = 1;
            this.txt_BlockSize.Text = "4";
            // 
            // label6
            // 
            this.label6.AutoSize = true;
            this.label6.Location = new System.Drawing.Point(28, 74);
            this.label6.Name = "label6";
            this.label6.Size = new System.Drawing.Size(59, 12);
            this.label6.TabIndex = 0;
            this.label6.Text = "卡片数量:";
            // 
            // txt_BlockCount
            // 
            this.txt_BlockCount.Location = new System.Drawing.Point(96, 71);
            this.txt_BlockCount.Name = "txt_BlockCount";
            this.txt_BlockCount.Size = new System.Drawing.Size(35, 21);
            this.txt_BlockCount.TabIndex = 1;
            this.txt_BlockCount.Text = "2";
            // 
            // label7
            // 
            this.label7.AutoSize = true;
            this.label7.Location = new System.Drawing.Point(152, 19);
            this.label7.Name = "label7";
            this.label7.Size = new System.Drawing.Size(35, 12);
            this.label7.TabIndex = 0;
            this.label7.Text = "结果:";
            // 
            // txt_Resulte
            // 
            this.txt_Resulte.Location = new System.Drawing.Point(207, 16);
            this.txt_Resulte.Multiline = true;
            this.txt_Resulte.Name = "txt_Resulte";
            this.txt_Resulte.ReadOnly = true;
            this.txt_Resulte.Size = new System.Drawing.Size(274, 77);
            this.txt_Resulte.TabIndex = 1;
            // 
            // label9
            // 
            this.label9.AutoSize = true;
            this.label9.Location = new System.Drawing.Point(35, 101);
            this.label9.Name = "label9";
            this.label9.Size = new System.Drawing.Size(47, 12);
            this.label9.TabIndex = 2;
            this.label9.Text = "写数据:";
            // 
            // txt_WriteData
            // 
            this.txt_WriteData.Location = new System.Drawing.Point(96, 98);
            this.txt_WriteData.Name = "txt_WriteData";
            this.txt_WriteData.Size = new System.Drawing.Size(385, 21);
            this.txt_WriteData.TabIndex = 3;
            // 
            // group_Paramter
            // 
            this.group_Paramter.Controls.Add(this.txt_WriteData);
            this.group_Paramter.Controls.Add(this.label9);
            this.group_Paramter.Controls.Add(this.txt_Resulte);
            this.group_Paramter.Controls.Add(this.label7);
            this.group_Paramter.Controls.Add(this.txt_BlockCount);
            this.group_Paramter.Controls.Add(this.label6);
            this.group_Paramter.Controls.Add(this.txt_BlockSize);
            this.group_Paramter.Controls.Add(this.label5);
            this.group_Paramter.Controls.Add(this.txt_StartAdress);
            this.group_Paramter.Controls.Add(this.label4);
            this.group_Paramter.Location = new System.Drawing.Point(266, 3);
            this.group_Paramter.Name = "group_Paramter";
            this.group_Paramter.Size = new System.Drawing.Size(487, 122);
            this.group_Paramter.TabIndex = 2;
            this.group_Paramter.TabStop = false;
            this.group_Paramter.Text = "参数：";
            this.group_Paramter.Enter += new System.EventHandler(this.group_Paramter_Enter);
            // 
            // list_Info
            // 
            this.list_Info.FormattingEnabled = true;
            this.list_Info.ItemHeight = 12;
            this.list_Info.Location = new System.Drawing.Point(10, 13);
            this.list_Info.Name = "list_Info";
            this.list_Info.Size = new System.Drawing.Size(581, 256);
            this.list_Info.TabIndex = 0;
            // 
            // groupBox4
            // 
            this.groupBox4.Controls.Add(this.list_Info);
            this.groupBox4.Location = new System.Drawing.Point(266, 127);
            this.groupBox4.Name = "groupBox4";
            this.groupBox4.Size = new System.Drawing.Size(597, 274);
            this.groupBox4.TabIndex = 3;
            this.groupBox4.TabStop = false;
            this.groupBox4.Text = "输出信息:";
            // 
            // btn_CloseSystem
            // 
            this.btn_CloseSystem.Location = new System.Drawing.Point(759, 101);
            this.btn_CloseSystem.Name = "btn_CloseSystem";
            this.btn_CloseSystem.Size = new System.Drawing.Size(104, 23);
            this.btn_CloseSystem.TabIndex = 4;
            this.btn_CloseSystem.Text = "退出系统";
            this.btn_CloseSystem.UseVisualStyleBackColor = true;
            this.btn_CloseSystem.Click += new System.EventHandler(this.btn_CloseSystem_Click);
            // 
            // btn_Clear
            // 
            this.btn_Clear.Location = new System.Drawing.Point(759, 77);
            this.btn_Clear.Name = "btn_Clear";
            this.btn_Clear.Size = new System.Drawing.Size(104, 23);
            this.btn_Clear.TabIndex = 4;
            this.btn_Clear.Text = "清除文本";
            this.btn_Clear.UseVisualStyleBackColor = true;
            this.btn_Clear.Click += new System.EventHandler(this.btn_Clear_Click);
            // 
            // Main
            // 
            this.AcceptButton = this.btn_Run;
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(865, 407);
            this.Controls.Add(this.btn_Clear);
            this.Controls.Add(this.btn_CloseSystem);
            this.Controls.Add(this.groupBox4);
            this.Controls.Add(this.group_Paramter);
            this.Controls.Add(this.group_Operate);
            this.Controls.Add(this.group_Port);
            this.MaximizeBox = false;
            this.MinimumSize = new System.Drawing.Size(874, 445);
            this.Name = "Main";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "RFID 练习测试";
            this.FormClosing += new System.Windows.Forms.FormClosingEventHandler(this.Main_FormClosing);
            this.Load += new System.EventHandler(this.Main_Load);
            this.group_Port.ResumeLayout(false);
            this.group_Port.PerformLayout();
            this.group_Operate.ResumeLayout(false);
            this.group_Operate.PerformLayout();
            this.groupBox5.ResumeLayout(false);
            this.groupBox5.PerformLayout();
            this.group_Paramter.ResumeLayout(false);
            this.group_Paramter.PerformLayout();
            this.groupBox4.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.GroupBox group_Port;
        private System.Windows.Forms.ComboBox cmb_Port;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Button btn_Refresh;
        private System.Windows.Forms.Button btn_Close;
        private System.Windows.Forms.Button btn_Open;
        private System.Windows.Forms.ComboBox cmb_Baud;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.GroupBox group_Operate;
        private System.Windows.Forms.Button btn_AutoRun;
        private System.Windows.Forms.Button btn_Run;
        private System.Windows.Forms.RadioButton rb_ReadMultiple;
        private System.Windows.Forms.RadioButton rb_ResetReady;
        private System.Windows.Forms.RadioButton rb_StayQuiet;
        private System.Windows.Forms.RadioButton rb_WriteSingle;
        private System.Windows.Forms.RadioButton rb_ReadSingle;
        private System.Windows.Forms.RadioButton rb_Inventory;
        private System.Windows.Forms.ComboBox cmb_Reset;
        private System.Windows.Forms.Label label8;
        private System.Windows.Forms.ComboBox cmb_tags;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.RadioButton rb_AutoInvertory;
        private System.Windows.Forms.GroupBox groupBox5;
        private System.Windows.Forms.RadioButton rb_fsk;
        private System.Windows.Forms.RadioButton rb_ask;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TextBox txt_StartAdress;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.TextBox txt_BlockSize;
        private System.Windows.Forms.Label label6;
        private System.Windows.Forms.TextBox txt_BlockCount;
        private System.Windows.Forms.Label label7;
        private System.Windows.Forms.TextBox txt_Resulte;
        private System.Windows.Forms.Label label9;
        private System.Windows.Forms.TextBox txt_WriteData;
        private System.Windows.Forms.GroupBox group_Paramter;
        private System.Windows.Forms.ListBox list_Info;
        private System.Windows.Forms.GroupBox groupBox4;
        private System.Windows.Forms.Button btn_CloseSystem;
        private System.Windows.Forms.Button btn_Clear;
    }
}

