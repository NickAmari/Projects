namespace Homework1Forms
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.stringBox = new System.Windows.Forms.TextBox();
            this.label1 = new System.Windows.Forms.Label();
            this.vowelLabel = new System.Windows.Forms.Label();
            this.upperCount = new System.Windows.Forms.Label();
            this.lowerCount = new System.Windows.Forms.Label();
            this.digitCountL = new System.Windows.Forms.Label();
            this.vowelBox = new System.Windows.Forms.TextBox();
            this.uppperBox = new System.Windows.Forms.TextBox();
            this.digitBox = new System.Windows.Forms.TextBox();
            this.lowerBox = new System.Windows.Forms.TextBox();
            this.button1 = new System.Windows.Forms.Button();
            this.label2 = new System.Windows.Forms.Label();
            this.revStringBox = new System.Windows.Forms.TextBox();
            this.SuspendLayout();
            // 
            // stringBox
            // 
            this.stringBox.Location = new System.Drawing.Point(201, 31);
            this.stringBox.Name = "stringBox";
            this.stringBox.Size = new System.Drawing.Size(493, 20);
            this.stringBox.TabIndex = 0;
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(37, 26);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(158, 24);
            this.label1.TabIndex = 1;
            this.label1.Text = "Type String Here:";
            // 
            // vowelLabel
            // 
            this.vowelLabel.AutoSize = true;
            this.vowelLabel.Location = new System.Drawing.Point(38, 122);
            this.vowelLabel.Name = "vowelLabel";
            this.vowelLabel.Size = new System.Drawing.Size(70, 13);
            this.vowelLabel.TabIndex = 2;
            this.vowelLabel.Text = "Vowel Count:";
            // 
            // upperCount
            // 
            this.upperCount.AutoSize = true;
            this.upperCount.Location = new System.Drawing.Point(250, 122);
            this.upperCount.Name = "upperCount";
            this.upperCount.Size = new System.Drawing.Size(93, 13);
            this.upperCount.TabIndex = 3;
            this.upperCount.Text = "Uppercase Count:";
            // 
            // lowerCount
            // 
            this.lowerCount.AutoSize = true;
            this.lowerCount.Location = new System.Drawing.Point(250, 163);
            this.lowerCount.Name = "lowerCount";
            this.lowerCount.Size = new System.Drawing.Size(93, 13);
            this.lowerCount.TabIndex = 4;
            this.lowerCount.Text = "Lowercase Count:";
            // 
            // digitCountL
            // 
            this.digitCountL.AutoSize = true;
            this.digitCountL.Location = new System.Drawing.Point(38, 163);
            this.digitCountL.Name = "digitCountL";
            this.digitCountL.Size = new System.Drawing.Size(62, 13);
            this.digitCountL.TabIndex = 5;
            this.digitCountL.Text = "Digit Count:";
            // 
            // vowelBox
            // 
            this.vowelBox.Location = new System.Drawing.Point(114, 119);
            this.vowelBox.Name = "vowelBox";
            this.vowelBox.ReadOnly = true;
            this.vowelBox.Size = new System.Drawing.Size(100, 20);
            this.vowelBox.TabIndex = 6;
            // 
            // uppperBox
            // 
            this.uppperBox.Location = new System.Drawing.Point(349, 119);
            this.uppperBox.Name = "uppperBox";
            this.uppperBox.ReadOnly = true;
            this.uppperBox.Size = new System.Drawing.Size(100, 20);
            this.uppperBox.TabIndex = 7;
            // 
            // digitBox
            // 
            this.digitBox.Location = new System.Drawing.Point(115, 160);
            this.digitBox.Name = "digitBox";
            this.digitBox.ReadOnly = true;
            this.digitBox.Size = new System.Drawing.Size(100, 20);
            this.digitBox.TabIndex = 8;
            // 
            // lowerBox
            // 
            this.lowerBox.Location = new System.Drawing.Point(349, 160);
            this.lowerBox.Name = "lowerBox";
            this.lowerBox.ReadOnly = true;
            this.lowerBox.Size = new System.Drawing.Size(100, 20);
            this.lowerBox.TabIndex = 9;
            // 
            // button1
            // 
            this.button1.Location = new System.Drawing.Point(700, 29);
            this.button1.Name = "button1";
            this.button1.Size = new System.Drawing.Size(75, 23);
            this.button1.TabIndex = 10;
            this.button1.Text = "Go";
            this.button1.UseVisualStyleBackColor = true;
            this.button1.Click += new System.EventHandler(this.button1_Click);
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(37, 70);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(149, 24);
            this.label2.TabIndex = 11;
            this.label2.Text = "Reversed String:";
            // 
            // revStringBox
            // 
            this.revStringBox.Location = new System.Drawing.Point(201, 75);
            this.revStringBox.Name = "revStringBox";
            this.revStringBox.ReadOnly = true;
            this.revStringBox.Size = new System.Drawing.Size(493, 20);
            this.revStringBox.TabIndex = 12;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(798, 206);
            this.Controls.Add(this.revStringBox);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.button1);
            this.Controls.Add(this.lowerBox);
            this.Controls.Add(this.digitBox);
            this.Controls.Add(this.uppperBox);
            this.Controls.Add(this.vowelBox);
            this.Controls.Add(this.digitCountL);
            this.Controls.Add(this.lowerCount);
            this.Controls.Add(this.upperCount);
            this.Controls.Add(this.vowelLabel);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.stringBox);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.TextBox stringBox;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label vowelLabel;
        private System.Windows.Forms.Label upperCount;
        private System.Windows.Forms.Label lowerCount;
        private System.Windows.Forms.Label digitCountL;
        private System.Windows.Forms.TextBox vowelBox;
        private System.Windows.Forms.TextBox uppperBox;
        private System.Windows.Forms.TextBox digitBox;
        private System.Windows.Forms.TextBox lowerBox;
        private System.Windows.Forms.Button button1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.TextBox revStringBox;
    }
}

