INSERT INTO CATEGORY (name, description, image)
VALUES
    ('Ciasto','słodkie przekąski', 'cake'),
    ('Obiad','dania główne', 'dinner.jpg'),
    ('Sałatka','lekkie przekąski', 'salad.jpg');

INSERT INTO RECIPE (name, description, likes, category_id)
VALUES
    ('Sałatka pieczarkowa', 'sałatka z pieczarkami',12,3),
    ('Pizza', 'pizza z szynką i pieczarkami',1,2),
    ('Pierniczki', 'ciastka z lukrem', 125,1),
    ('Zapiekanka', 'zapiekanka z szynką i ziemniakami', 20,2),
    ('Sernik', 'sernik na herbatnikach z brzoskwinią', 2, 1),
    ('Zupa pomidorowa', 'zupa pomidorowa z ryżem i śmietaną', 4, 2),
    ('Pizza', 'pizza z szynką i pieczarkami', 1,2),
    ('Barszcz', 'barszcz czerwony',10,2);

