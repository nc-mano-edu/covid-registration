--insert into roles (name, description)
--values ('Doctor', 'Doctor descr'),
--        ('Patient', 'Patient descr');

insert into attribute_types (name, check_mask)
values ('String value', '[A-Za-zА-Яа-яЁё\s]+'),
       ('Numeric value', '\d+(\.\d+)?'),
       ('Image value', '.{1,1024}'),
       ('Date value', '\d{4}-[01]\d-[0-3]\d(\s[0-2]\d:[0-5]\d:[0-5]\d)?(?:\.\d+)?Z?'),
       ('Phone number value', '[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\s\./0-9]*');

insert into attributes (name, attribute_type_id)
values ('Account photo', 3),
       ('Age', 2),
       ('Contact phone number', 5),
       ('Blood test results', 1),
       ('Date of the application', 4),
       ('Service payment amount', 2),
       ('Photo of inflammation on the skin', 3),
       ('End date of treatment', 4),
       ('Clinic link', 3),
       ('Description of patient complaints', 1);

insert into tasks(name, schedule, description)
values ('Completion of registration', '0 12 */2 * *', 'Complete filling of the user account'),
       ('Request for treatment', '0 12 * * *', 'Filling out a treatment request'),
       ('Payment for treatment', '0 */5 * * *', 'Confirmation of completion of treatment and payment');

insert into task_attributes(task_id, attribute_id)
values (1, 1),
       (1, 2),
       (1, 3),
       (2, 4),
       (2, 5),
       (2, 7),
       (2, 9),
       (2, 10),
       (3, 6),
       (3, 8);

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



