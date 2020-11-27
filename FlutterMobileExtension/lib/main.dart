import 'package:flutter/material.dart';
import 'screens/home/Homepage.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(title: 'Event Manager', home: Homepage());
  }
}
