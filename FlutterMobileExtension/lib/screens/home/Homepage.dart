import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;
import "package:tubitak_intern_mobile_project/models/Event.dart";

import 'dart:async';
import 'dart:convert';

import 'package:tubitak_intern_mobile_project/screens/home/EventCard.dart';

final String uri = "http://10.0.2.2:8080/events";

class Homepage extends StatefulWidget {
  Homepage();

  @override
  State<StatefulWidget> createState() {
    return _HomepageState();
  }
}

class _HomepageState extends State<Homepage> {
  Future<List<Event>> getData() async {
    var response = await http.get(uri, headers: {"accept": "application/json"});
    List<Event> temp = List();

    if (response.statusCode == 200) {
      List jsonResponse = json.decode(response.body);
      return jsonResponse.map((event) => new Event.fromJson(event)).toList();
    } else {
      throw Exception('Failed to load jobs from API');
    }
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(title: Text("Homepage")),
        body: FutureBuilder<List<Event>>(
            future: getData(),
            builder: (context, projectSnap) {
              if (!projectSnap.hasData) {
                return Container(
                  child: Center(
                    child: CircularProgressIndicator(),
                  ),
                );
              }
              List<Event> data = projectSnap.data;

              return ListView.builder(
                  itemCount: data.length,
                  itemBuilder: (context, index) {
                    Event event = projectSnap.data[index];
                    return EventCard(event: event);
                  });
            }));
  }
}
