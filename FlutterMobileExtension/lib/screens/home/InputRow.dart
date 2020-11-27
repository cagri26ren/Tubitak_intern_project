import 'package:flutter/material.dart';

class InputRow extends StatelessWidget {
  final Function submitHandler;
  final double theWidth;
  final String name;
  final TextInputType textInputType;

  InputRow({this.textInputType, this.theWidth, this.name, this.submitHandler});

  @override
  Widget build(BuildContext context) {
    return Padding(
      padding: EdgeInsets.all(10),
      child: Row(
        children: [
          Text(
            "${name} :",
            textAlign: TextAlign.left,
            style: TextStyle(fontSize: 20),
          ),
          SizedBox(
            child: Container(
              width: theWidth,
              child: TextField(
                keyboardType: textInputType,
                decoration: InputDecoration(
                  border: OutlineInputBorder(),
                  labelText: name,
                ),
                onChanged: (value) => {submitHandler(value)},
              ),
            ),
          ),
        ],
      ),
    );
  }
}
