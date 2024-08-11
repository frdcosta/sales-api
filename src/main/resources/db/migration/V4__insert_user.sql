INSERT INTO public.users
(id, email, "password", created_at)
VALUES(nextval('users_id_seq'::regclass), 'login@cilia.com.br', '$2a$10$eEgU3bCngDjR5VV061uH8..1SHnpvTCmlQQMB8IoTs3QSI7jhwq96', CURRENT_TIMESTAMP);