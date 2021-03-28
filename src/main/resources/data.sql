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

insert into task_instances(task_id, request_id, created_time, finished_time, is_active)
values (1, 1, TIMESTAMP '2021-01-16 15:36', TIMESTAMP '2021-01-16 23:15', false),
       (1, 2, TIMESTAMP '2021-01-17 10:00', TIMESTAMP '2021-01-18 08:36', false),
       (2, 1, TIMESTAMP '2021-02-22 15:30', TIMESTAMP '2021-02-25 18:00', false),
       (2, 2, TIMESTAMP '2021-02-25 07:15', TIMESTAMP '2021-02-25 19:55', false),
       (3, 1, TIMESTAMP '2021-02-25 07:15', null, true),
       (3, 2, TIMESTAMP '2021-02-26 15:39', null, true);

insert into task_instance_data(task_instance_id, attribute_id, date_value, image_value, numeric_value, string_value)
values (1, 2, null, null, 20, null),
       (2, 3, null, null, null, '+79011235629'),
       (3, 4, null, null, null, 'normal'),
       (4, 5, TIMESTAMP '2021-02-26 15:39', null, null, null),
       (5, 6, null, null, 2000, null),
       (6, 8, TIMESTAMP '2021-03-08 08:00', null, null, null);

