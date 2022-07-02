CREATE OR REPLACE FUNCTION map_point_id(mpName VARCHAR)
    RETURNS BIGINT
AS
'SELECT id
 FROM map_points mp
 WHERE mp.name = $1'
    LANGUAGE SQL;

CREATE OR REPLACE FUNCTION car_type_id(ctName VARCHAR)
    RETURNS BIGINT
AS
'SELECT id
 FROM car_types ct
 WHERE ct.name = $1'
    LANGUAGE SQL;

TRUNCATE map_points, car_types CASCADE;

insert into map_points(name, created_at)
VALUES ('map_point 1', NOW()),
       ('map_point 2', NOW());

insert into car_types(name, description)
values ('Легковой', ''),
       ('Грузовик 5', '5Т'),
       ('Грузовик 10', '10Т'),
       ('Грузовик + прицеп', '10Т'),
       ('Грузовик', '20Т'),
       ('Грузовик + прицеп', '20Т'),
       ('Сцепка еврофура', ''),
       ('Сцепка негабарит', ''),
       ('Спецтранспорт', '');

insert into map_point_car_type_links (map_point_id, car_type_id)
VALUES (map_point_id('map_point 1'), car_type_id('Легковой')),
       (map_point_id('map_point 1'), car_type_id('Грузовик 5')),
       (map_point_id('map_point 2'), car_type_id('Грузовик 10')),
       (map_point_id('map_point 2'), car_type_id('Грузовик + прицеп'));
