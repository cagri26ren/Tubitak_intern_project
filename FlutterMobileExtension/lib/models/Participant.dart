import 'package:flutter/material.dart';
import 'package:tubitak_intern_mobile_project/models/Question.dart';
import 'package:tubitak_intern_mobile_project/models/SurveyQuestion.dart';



class Participant {
  String name;
  String surname;
  String email;
  String tcKimlikNo;
  String joinDate;
  String joinTime;
  int age;
  String gender;
  List<Question> answers;
  List<SurveyQuestion> surveyQuestions;

  Participant(
      {this.name,
      this.surname,
      this.email,
      this.tcKimlikNo,
      this.joinDate,
      this.joinTime,
      this.age,
      this.gender,
      this.answers,
      this.surveyQuestions});
}
