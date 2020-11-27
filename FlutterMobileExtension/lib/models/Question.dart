import 'package:flutter/material.dart';

class Question {
  final String questionName;
  String answer;

  Question({this.questionName, this.answer});

  factory Question.fromJson(Map<String, dynamic> json) {
    return Question(questionName: json['questionName'], answer: '');
  }

  Map<String, dynamic> toJson() {
    return {'questionName': questionName, 'answer': answer};
  }
}
