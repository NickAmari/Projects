using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace CSE445_Homework2_3
{
    class TravelAgency
    {
        int id;
        public static event orderCreatedEvent orderCreated;
        public Random rnd = new Random();
        public encoder_decoder e_d = new encoder_decoder();

        public TravelAgency(int p)
        {
            id = p;
        }
        public void AgencyFunc() // startingthread
        {
            while (Program.AirlineThread1Running || Program.AirlineThread2Running)
            {
                Thread.Sleep(rnd.Next(1500, 3000));
            }
        }

        public void createOrder(int senderID)
        {
            Int32 cardNo = rnd.Next(4750, 7250); //generates a random valid credit card number
            Int32 amount = rnd.Next(10, 100);

            OrderObject oo = new OrderObject(id, cardNo, amount); //creates orderObject with generated data
            string orderString = e_d.encode(oo); //encodes orderObject into a string

            Console.WriteLine("Agency {0}'s order has been created at {1} with Airline {2}.", id, DateTime.Now.ToString("hh:mm:ss"), senderID);

            Program.mcb.setOneCell(orderString); //inserts order into the MultiCellBuffer
            orderCreated(); //emits event
        }

        public void orderProcessed(int senderID, Int32 amountToCharge) // Event handler when the order is processed
        {
            Console.WriteLine("Agency {0}'s order has been processed. The amount to be charged is $" + amountToCharge, senderID);
        }

        public void TicketsOnSale(int senderID) // Event handler for priceCut
        {   
            createOrder(senderID);
        }
    }
}
