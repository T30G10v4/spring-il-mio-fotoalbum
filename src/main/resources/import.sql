INSERT INTO photos(description, image, title, visible) VALUES ( 'Foto di paesaggio' , 'asd' , 'Paesaggio 1' , true );
INSERT INTO photos(description, image, title, visible) VALUES ( 'Foto di persona' , 'qwe' , 'Persona 1' , true );
INSERT INTO photos(description, image, title, visible) VALUES ( 'Foto di Auto' , 'zxc' , 'Auto 1' , true );

INSERT INTO caregories(name) VALUES ( 'Paesaggi' );
INSERT INTO caregories(name) VALUES ( 'Persone' );
INSERT INTO caregories(name) VALUES ( 'Auto' );

INSERT INTO photo_category(photo_id, category_id) VALUES (1,1)
INSERT INTO photo_category(photo_id, category_id) VALUES (2,2)
INSERT INTO photo_category(photo_id, category_id) VALUES (3,3)