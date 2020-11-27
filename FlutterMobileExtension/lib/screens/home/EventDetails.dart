import 'package:flutter/material.dart';
import 'package:flutter/rendering.dart';
import 'package:tubitak_intern_mobile_project/models/Event.dart';
import 'package:tubitak_intern_mobile_project/models/Participant.dart';
import 'package:tubitak_intern_mobile_project/models/Question.dart';
import 'package:tubitak_intern_mobile_project/models/SurveyQuestion.dart';
import 'package:tubitak_intern_mobile_project/screens/home/InputRow.dart';

import 'package:http/http.dart' as http;

import 'dart:async';
import 'dart:convert';

class EventDetails extends StatefulWidget {
  final Event event;

  EventDetails({this.event});

  @override
  State<StatefulWidget> createState() {
    return _EventDetailsState();
  }
}

int answerIndex = -1;

class _EventDetailsState extends State<EventDetails> {
  Participant participant;

  @protected
  @mustCallSuper
  void initState() {
    answerIndex = 0;
    participant = new Participant();
    participant.name = "ali";

    participant.answers = widget.event.questions;

    print(widget.event.surveyQuestions[0].surveyQuestionName);
    participant.surveyQuestions = widget.event.surveyQuestions;
    participant.age = 20;
    participant.gender = 'male';
  }

  void postData() async {
    final String uri = "http://10.0.2.2:8080/events/join/${widget.event.name}";

    List<Map> aJson = new List();
    for (int i = 0; i < participant.answers.length; i++) {
      Question question = participant.answers[i];
      aJson.add(question.toJson());
    }
    List<Map> surveyJson = new List();
    for (int i = 0; i < participant.surveyQuestions.length; i++) {
      SurveyQuestion question = participant.surveyQuestions[i];
      surveyJson.add(question.toJson());
    }

    final http.Response response = await http.put(
      uri,
      headers: {'Content-type': 'application/json'},
      body: json.encode({
        'name': participant.name,
        'surname': participant.surname,
        'email': participant.email,
        'tcKimlikNo': "13082197360",
        'joinDate': '',
        'joinTime': '',
        'age': participant.age,
        'gender': participant.gender,
        'answers': aJson,
        'surveyQuestions': surveyJson
      }),
    );
    print(uri);
    print(json.encode({
      'name': participant.name,
      'surname': participant.surname,
      'email': participant.email,
      'tcKimlikNo': participant.tcKimlikNo,
      'joinDate': '',
      'joinTime': '',
      'age': participant.age,
      'gender': participant.gender,
      'answers': participant.answers,
      'surveyQuestions': participant.surveyQuestions
    }));
    print(response.body);
  }

  @override
  Widget build(BuildContext context) {
    answerIndex = -1;
    return Scaffold(
      appBar: AppBar(
        title: Text("Join Event"),
      ),
      body: Container(
        child: SingleChildScrollView(
          child: Column(
            children: [
              Text(
                '${widget.event.name}',
                style: TextStyle(fontSize: 30, fontWeight: FontWeight.bold),
              ),
              InputRow(
                textInputType: TextInputType.name,
                theWidth: 150.0,
                name: "Name",
                submitHandler: (value) {
                  setState(() {
                    participant.name = value;
                  });
                },
              ),
              InputRow(
                textInputType: TextInputType.name,
                theWidth: 150.0,
                name: "Surname",
                submitHandler: (value) {
                  setState(() {
                    participant.surname = value;
                  });
                },
              ),
              InputRow(
                textInputType: TextInputType.number,
                theWidth: 250.0,
                name: "T.C.",
                submitHandler: (value) {
                  setState(() {
                    participant.tcKimlikNo = value;
                  });
                },
              ),
              InputRow(
                textInputType: TextInputType.emailAddress,
                theWidth: 250.0,
                name: "Email",
                submitHandler: (value) {
                  setState(() {
                    participant.email = value;
                  });
                },
              ),
              if (participant.answers.length != 0)
                Align(
                  alignment: Alignment.centerLeft,
                  child: Text(
                    'These are the questions event ${widget.event.name} want to know',
                    style: TextStyle(fontSize: 15, fontWeight: FontWeight.bold),
                  ),
                ),
              if (participant.answers.length != 0)
                Align(
                  alignment: Alignment.centerLeft,
                  child: SizedBox(
                    width: 350,
                    child: Divider(
                      thickness: 3.0,
                      color: Colors.black,
                    ),
                  ),
                ),
              if (widget.event.askAge != null && widget.event.askAge == true)
                InputRow(
                  textInputType: TextInputType.number,
                  theWidth: 100.0,
                  name: "Age",
                  submitHandler: (value) {
                    setState(() {
                      participant.age = value;
                    });
                  },
                ),
              if (widget.event.askGender != null &&
                  widget.event.askGender == true)
                InputRow(
                  textInputType: TextInputType.text,
                  theWidth: 150.0,
                  name: "Gender",
                  submitHandler: (value) {
                    setState(() {
                      participant.gender = value;
                    });
                  },
                ),
              ...(participant.answers).map((question) {
                answerIndex++;
                return InputRow(
                    textInputType: TextInputType.text,
                    theWidth: 300.0,
                    name: question.questionName,
                    submitHandler: (value) {
                      setState(() {
                        participant.answers[answerIndex].answer = value;
                      });
                    });
              }),
              Align(
                  alignment: Alignment.centerRight,
                  child: Padding(
                    padding: EdgeInsets.all(30),
                    child: RaisedButton(
                      color: Colors.blue,
                      child: Container(
                        width: 80,
                        child: Text(
                          "SUBMIT",
                          textAlign: TextAlign.center,
                          style: TextStyle(fontSize: 18, color: Colors.white),
                        ),
                      ),
                      onPressed: () {
                        postData();
                      },
                    ),
                  )),
            ],
          ),
        ),
      ),
    );
  }
}
