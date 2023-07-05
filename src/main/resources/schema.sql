-- テーブル削除
DROP TABLE IF EXISTS spots;
DROP TABLE IF EXISTS prefs;
DROP TABLE IF EXISTS inns;
DROP TABLE IF EXISTS inns_detail;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS bokkings;
DROP TABLE IF EXISTS plans;
DROP TABLE IF EXISTS restaurants;

-- spots Table
CREATE TABLE spots
(
   id SERIAL PRIMARY KEY,
   pref_id INTEGER,
   img1 TEXT,
   img2 TEXT,
   img3 TEXT,
   name TEXT,
   location TEXT,
   outline TEXT,
   access TEXT,
   price TEXT
);

-- Prefs Table
CREATE TABLE prefs
(
   id SERIAL PRIMARY KEY,
   name TEXT
);

-- Inns Table
CREATE TABLE inns
(
   id SERIAL PRIMARY KEY,
   spot_id INTEGER,
   img TEXT,
   name TEXT,
   outline TEXT
  );
  
 --Inns_detail
 CREATE TABLE inns_detail
 (
   id SERIAL PRIMARY KEY,
   inn_id INTEGER,
   name TEXT,
   img1 TEXT,
   img2 TEXT,
   img3 TEXT,
   location TEXT,
   access TEXT,
   type TEXT,
   meal TEXT,
   price TEXT
);

-- ユーザーテーブル
CREATE TABLE users
(
  id SERIAL PRIMARY KEY,
  name TEXT,
  address TEXT,
  tel TEXT,
  email TEXT, 
  password TEXT 
);

-- Bookingsテーブル
CREATE TABLE bokkings
(
  id SERIAL PRIMARY KEY,
  inn_id INTEGER,
  plan_id INTEGER,
  stay_date DATE,
  people INTEGER
);

-- Plansテーブル
CREATE TABLE plans
(
  id SERIAL PRIMARY KEY,
  plan TEXT
); 


-- Restaurantsテーブル
CREATE TABLE restaurants
(
  id SERIAL PRIMARY KEY,
  spot_id INTEGER,
  img TEXT,
  name TEXT,
  outline TEXT
); 


