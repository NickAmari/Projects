using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace CSE445_Homework2_3
{
    class encoder_decoder
    {

        public OrderObject decode(string str)
        {
            OrderObject order = new OrderObject(0, 0, 0);
            char[] delimiters = { ',' };
            string[] vals = str.Split(delimiters);
            order.setAmount(Convert.ToInt32(vals[0]));
            order.setcardNo(Convert.ToInt32(vals[1]));
            order.setsenderId(Convert.ToInt32(vals[2]));
            order.setreceiverId(Convert.ToInt32(vals[3]));

            return order;
        }


        public string encode(OrderObject obj)
        {
            string ret = "";
            string amo = obj.getAmount().ToString();
            string card = obj.getcardNo().ToString();
            string sender = obj.getsenderId().ToString();
            string receiver = obj.getreceiverId().ToString();

            ret = amo + "," + card + "," + sender + "," + receiver;

            return ret;

        }
    }
}
