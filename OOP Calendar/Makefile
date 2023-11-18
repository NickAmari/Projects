testFile: Assignment5.o Calendar.o Day.o Appointment.o Reminder.o
	g++ -Wall Assignment5.o Calendar.o Day.o Appointment.o Reminder.o -o testFile 

Assignment5.o: Assignment5.cpp Calendar.h Day.h Reminder.h Appointment.h
	g++ -Wall -c Assignment5.cpp

Calendar.o: Calendar.h Calendar.cpp Day.h Reminder.h Appointment.h
	g++ -Wall -c Calendar.cpp

Day.o: Day.h Day.cpp Reminder.h Appointment.h
	g++ -Wall -c Day.cpp

Appointment.o: Appointment.h Appointment.cpp Reminder.h
	g++ -Wall -c Appointment.cpp

Reminder.o: Reminder.h Reminder.cpp
	g++ -Wall -c Reminder.cpp

clean:
	\rm *.o *~ testFile

tar:
	tar -cvf Assignment5.cpp Calendar.h Calendar.cpp Day.h Day.cpp Reminder.h Reminder.cpp Appointment.h Appointment.cpp
