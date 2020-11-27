import 'package:flutter/material.dart';
import 'package:tubitak_intern_mobile_project/models/Question.dart';
import 'package:tubitak_intern_mobile_project/models/SurveyQuestion.dart';

class Event {
  final String name;
  final int max;
  final int current;
  final String startDate;
  final String startTime;
  final String endDate;
  final String endTime;
  final bool askAge;
  final bool askGender;
  List<Question> questions;
  List<SurveyQuestion> surveyQuestions;

  Event(
      {this.name,
      this.max,
      this.current,
      this.startDate,
      this.startTime,
      this.endDate,
      this.endTime,
      this.questions,
      this.askAge,
      this.askGender,
      this.surveyQuestions});

  factory Event.fromJson(Map<String, dynamic> json) {
    var questionObjsJson = json['questions'] as List;
    List<Question> questionObjs =
        questionObjsJson.map((tagJson) => Question.fromJson(tagJson)).toList();

    var surveyQuestionObjsJson = json['surveyQuestions'] as List;
    List<SurveyQuestion> surveyQuestionObjs = surveyQuestionObjsJson
        .map((tagJson) => SurveyQuestion.fromJson(tagJson))
        .toList();

    return Event(
        name: json['name'],
        max: json['max'],
        current: json['current'],
        startDate: json['startDate'],
        startTime: json['startTime'],
        endDate: json['endDate'],
        endTime: json['endTime'],
        askAge: json['askAge'],
        askGender: json['askGender'],
        questions: questionObjs,
        surveyQuestions: surveyQuestionObjs);
  }
}
