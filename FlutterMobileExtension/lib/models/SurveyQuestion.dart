import 'package:flutter/material.dart';

class SurveyQuestion {
  final String surveyQuestionName;
  String answer;

  SurveyQuestion({this.surveyQuestionName, this.answer});

  factory SurveyQuestion.fromJson(Map<String, dynamic> json) {
    return SurveyQuestion(
        surveyQuestionName: json['surveyQuestionName'], answer: '');
  }

  Map<String, dynamic> toJson() {
    return {'surveyQuestionName': surveyQuestionName, 'answer': answer};
  }
}
