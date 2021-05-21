insert into symptoms(name)
values ('Temperature'),
       ('Loss of taste'),
       ('Dry cough'),
       ('Wet cough'),
       ('Fatigue'),
       ('Headache'),
       ('Increased heart rate'),
       ('Sore throat'),
       ('Nasal congestion');

insert into attribute_types (name, check_mask)
values ('String value', '[A-Za-zА-Я0-9а-яЁё\s]+'),
       ('Numeric value', '\d+(\.\d+)?'),
       ('Image value', '.{1,1024}'),
       ('Date value', '\d{4}-[01]\d-[0-3]\d(\s[0-2]\d:[0-5]\d:[0-5]\d)?(?:\.\d+)?Z?'),
       ('Phone number value', '[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*'),
       ('Url value', '(?:http(s)?:\/\/)?[\w.-]+(?:\.[\w\.-]+)+[\w\-\._~:/?#[\]@!\$&''\(\)\*\+,;=.]+');

insert into roles (name, description)
  values
    ('Patient', 'Patient role; default for all users'),
    ('Doctor', 'Doctor role; can cure people'),
    ('Admin', 'Administrator');

insert into covid_users(id, date_of_birth, first_name, insurance_number, last_name, middle_name, phone_number, email, username, password)
    values
        (1, TIMESTAMP '1986-06-04 00:00:00.000000', 'Peter', '382-305-637 21', 'Peterson', 'Jun.',
        '+7 (956) 517-57-46', 'patient@patient.com', 'patient', '1'),
        (2, TIMESTAMP '1986-06-04 00:00:00.000000', 'John', '382-305-637 21', 'Johnson', 'Snr.',
        '+7 (956) 517-57-46', 'doctor@doctor.com', 'doctor', '1');


insert into covid_users_roles (user_id, roles_role_id)
    values
        (1, 1),
        (2, 2);

insert into attributes (name, attribute_type_id)
values ('Personal photo', 3),
       ('Age', 2),
       ('Weight', 1),
       ('Chronic diseases, if there are any:', 1),
       ('Contact phone number', 5),
       ('Blood test results', 3),
       ('PCR test results', 3),
       ('Date of the application', 4),
       ('Service payment amount', 2),
       ('Photo of inflammation on the skin', 3),
       ('End date of treatment', 4),
       ('Clinic link', 6),
       ('Description of patient complaints', 1);

insert into tasks(name, schedule, description)
values ('Provide personal data', '0 0 12 */2 * *', 'Complete filling of the user account'),
       ('Blood test results', '0 0 12 * * *', 'Please attach blood test results'),
       ('PCR testing results', '0 0 */5 * * *', 'Please attach PCR testing result');

insert into task_attributes(task_id, attribute_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (1, 4),
       (1, 5),
       (2, 6),
       (2, 8),
       (3, 7),
       (3, 8);