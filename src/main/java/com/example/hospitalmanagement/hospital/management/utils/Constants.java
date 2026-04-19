package com.example.hospitalmanagement.hospital.management.utils;

public class Constants {

    public static final String TBL_DOCTOR =
            "doctor(id, name, gender, speciality, shift_time, email, phone, age, experience, education)";
    public static final String TBL_APPOINTMENT =
            "appointment(id, patientId, doctorId, date)";
    public static final String TBL_BILL =
            "bill(id, patientId, amount, status)";
    public static final String TBL_PATIENT =
            "patient(id, name, gender, age)";

    // ✅ Simplified and effective prompt for small models like orca-mini
    public static final String BASE_PROMPT = """
help me getting SQL queries for a hospital database.

Here are the tables:
- doctor(id, name, gender, speciality, shift_time, email, phone, age, experience, education) this is doctor table schema
- appointment(id, patientId, doctorId, date) this is appointment table schema
- bill(id, patientId, amount, status) this is bill table schema
- patient(id, name, gender, age) this is patient table schema

provide the query based on user requirement understand user requirement and then lock up the schema table and then response and don't change anything that user request any data of any attribute like columns value
Return only the SQL query, no explanation and don't provide other information just give provide the sql query according to the user request.
No Text before the query and no text after the query return just the query

User request:
""";
}
