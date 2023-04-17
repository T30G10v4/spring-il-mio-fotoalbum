INSERT INTO photos(description, image, title, visible) VALUES ( 'Foto di paesaggio' , 'asd' , 'Paesaggio 1' , true )
INSERT INTO photos(description, image, title, visible) VALUES ( 'Foto di persona' , 'qwe' , 'Persona 1' , true )
INSERT INTO photos(description, image, title, visible) VALUES ( 'Foto di Auto' , 'zxc' , 'Auto 1' , true )

INSERT INTO categories(name) VALUES ( 'Paesaggi' )
INSERT INTO categories(name) VALUES ( 'Persone' )
INSERT INTO categories(name) VALUES ( 'Auto' )

INSERT INTO photo_category(photo_id, category_id) VALUES (1,1)
INSERT INTO photo_category(photo_id, category_id) VALUES (2,2)
INSERT INTO photo_category(photo_id, category_id) VALUES (3,3)

INSERT INTO messages(created_at, mail, text) VALUES ('2023-04-17 23:59:00','gigi@gmail.com','jkasdHFBASJKDHBFASJKDHFB')
INSERT INTO messages(created_at, mail, text) VALUES ('2022-04-18 15:18:00','pino@gmail.com','asdfasdfasdf')
INSERT INTO messages(created_at, mail, text) VALUES ('2023-04-19 13:26:00','lino@gmail.com','jkasdHFBASJKDHasdfasknjsdfkjbsdfbjn.sdfabjnBFASJKDHFB')