import 'package:flutter/material.dart';
import 'package:tubitak_intern_mobile_project/models/Event.dart';
import './EventDetails.dart';

class EventCard extends StatelessWidget {
  final Event event;

  EventCard({this.event});

  @override
  Widget build(BuildContext context) {
    return GestureDetector(
      onTap: () => {
        Navigator.push(context,
            MaterialPageRoute(builder: (context) => EventDetails(event: event)))
      },
      child: Card(
        color: Colors.blue,
        child: Column(
          children: [
            Padding(
              padding: const EdgeInsets.all(8.0),
              child: Text(
                event.name,
                style: TextStyle(fontSize: 20),
              ),
            ),
            Padding(
              padding: const EdgeInsets.only(bottom: 5, top: 5),
              child: Row(
                mainAxisAlignment: MainAxisAlignment.center,
                children: [
                  Padding(
                    padding: const EdgeInsets.only(right: 50),
                    child: Column(
                      children: [
                        Padding(
                            padding: EdgeInsets.only(bottom: 10.0),
                            child: Text(
                              "S.T: " +
                                  event.startDate.substring(8, 10) +
                                  "-" +
                                  event.startDate.substring(5, 7) +
                                  "-" +
                                  event.startDate.substring(0, 4) +
                                  " / " +
                                  (event.startTime).substring(0, 5),
                              style: TextStyle(fontSize: 20),
                            )),
                        Padding(
                            padding: EdgeInsets.only(bottom: 10.0),
                            child: Text(
                              "E.T: " +
                                  event.endDate.substring(8, 10) +
                                  "-" +
                                  event.endDate.substring(5, 7) +
                                  "-" +
                                  event.endDate.substring(0, 4) +
                                  " / " +
                                  (event.endTime).substring(0, 5),
                              style: TextStyle(fontSize: 20),
                            ))
                      ],
                    ),
                  ),
                  Text(
                    event.current.toString() + " / " + event.max.toString(),
                    style: TextStyle(fontSize: 20),
                  )
                ],
              ),
            ),
          ],
        ),
      ),
    );
  }
}
