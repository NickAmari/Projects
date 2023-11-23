using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Threading;

namespace CSE445_Homework2_3
{
    public delegate void priceCutEvent(int p);
    public delegate void orderProcessedEvent(int sendID, Int32 amountCharged);
    public delegate void orderCreatedEvent();
    class Program
    {
        public static mCellBuffer mcb;
        public static bool AirlineThread1Running = true;
        public static bool AirlineThread2Running = true;
        public static Int32[] airline1Nums = new Int32[3]; // priceCuts | compelte | Rejected
        public static Int32[] airline2Nums = new Int32[3]; // priceCuts | compelte | Rejected
        public static Int32 totalOrders;
        public static Int32 OrdersCompleted;
        public static Int32 OrdersRejected;
        static void Main(string[] args)
        {
            /*OrderObject oo = new OrderObject(1, 2, 3, 4);
            OrderObject o2 = new OrderObject(0, 0, 0, 0);
            encoder_decoder e_d = new encoder_decoder();

            string order = e_d.encode(oo);
            mcb.setOneCell(order);

            string res = mcb.getOneCell();          This all confirms encoder/decoder works with OrderObjects and the multicellbuffer
            o2 = e_d.decode(res);

            Console.WriteLine(res);
            Console.WriteLine(o2.getsenderId());
            Console.WriteLine(o2.getreceiverId());
            Console.WriteLine(o2.getcardNo());
            Console.WriteLine(o2.getAmount());*/
            totalOrders = 0;
            OrdersCompleted = 0;
            OrdersRejected = 0;
            Airline a1 = new Airline(1);
            Airline a2 = new Airline(2);
            TravelAgency ta1 = new TravelAgency(1);
            TravelAgency ta2 = new TravelAgency(2);
            TravelAgency ta3 = new TravelAgency(3);
            TravelAgency ta4 = new TravelAgency(4);
            TravelAgency ta5 = new TravelAgency(5);


            mcb = new mCellBuffer(); //initializes MultiCellBuffer

            Thread airlineRunner = new Thread(new ThreadStart(a1.airlineFunction));
            Thread airlineRunner2 = new Thread(new ThreadStart(a2.airlineFunction));

            Airline.priceCut += new priceCutEvent(ta1.TicketsOnSale); //When priceCut happens, travel agencies create orders
            Airline.priceCut += new priceCutEvent(ta2.TicketsOnSale);
            Airline.priceCut += new priceCutEvent(ta3.TicketsOnSale);
            Airline.priceCut += new priceCutEvent(ta4.TicketsOnSale);
            Airline.priceCut += new priceCutEvent(ta5.TicketsOnSale);
            TravelAgency.orderCreated += new orderCreatedEvent(a1.runOrder); //runOrder when the orderCreated event is emitted
            Airline.orderProcessed += new orderProcessedEvent(ta1.orderProcessed); //order processed confirmation callback

            airlineRunner.Start();
            airlineRunner2.Start();

            airlineRunner.Join();
            airlineRunner2.Join();

            totalOrders = OrdersCompleted + OrdersRejected;
            Console.Write("\n");
            Console.WriteLine("Airline Threads finished with " + totalOrders + " total orders." + "\n" +
                              "Airline 1 had " + airline1Nums[0].ToString() + " price cut events and Airline 2 had " + airline2Nums[0].ToString() + " price cut events.");
            Console.WriteLine("There was a total of " + airline1Nums[2].ToString() + " completed orders, and " + airline1Nums[1].ToString() + " rejected orders.");


        }
    }
}
