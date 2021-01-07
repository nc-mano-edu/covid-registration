insert into attribute_type (name, check_mask)
values ('String value', '[A-Za-zА-Яа-яЁё\s]+'),
       ('Numeric value', '\d+(\.\d+)?'),
       ('URL value', '[(http(s)?):\/\/(www\.)?a-zA-Z0-9@:%._\+~#=]{2,256}\.[a-z]{2,6}\b([-a-zA-Z0-9@:%_\+.~#?&//=]*)'),
       ('Date value', '\d{4}-[01]\d-[0-3]\d(\s[0-2]\d:[0-5]\d:[0-5]\d)?(?:\.\d+)?Z?');

