using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSE445_Homework2_3
{
    class OrderObject
    {
        public int senderId; // travel agency
        public int cardNo;
        public int receiverId; // airline
        public int amount;

        public OrderObject(int s, int c, int a)
        {
            this.senderId = s;
            this.cardNo = c;
            this.receiverId = 0;
            this.amount = a;
        }
        public void setcardNo(int val)
        {
            cardNo = val;
        }

        public void setAmount(int val)
        {
            amount = val;
        }
        public void setsenderId(int val)
        {
            senderId = val;
        }

        public void setreceiverId(int val)
        {
            receiverId = val;
        }

        public int getAmount()
        {
            return amount;
        }

        public int getcardNo()
        {
            return cardNo;
        }

        public int getsenderId()
        {
            return senderId;
        }

        public int getreceiverId()
        {
            return receiverId;
        }

    }
}