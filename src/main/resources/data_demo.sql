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