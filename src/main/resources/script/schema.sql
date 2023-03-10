-- -- SET foreign_key_checks = 0;
-- -- DROP TABLE IF EXISTS `Users`;
-- -- DROP TABLE IF EXISTS `Posts`;
-- -- DROP TABLE IF EXISTS `Comments`;
-- -- DROP TABLE IF EXISTS `UserDivision`;
-- -- DROP TABLE IF EXISTS `Likes`;
--
-- drop all objects;
--
-- CREATE TABLE `Users` (
--                          `user_id`	int	NOT NULL primary key auto_increment,
--                          `name`	nvarchar(20)	NOT NULL unique,
--                          `password`	nvarchar(20)	NOT NULL,
--                          `created_at`	datetime	NOT NULL,
--                          `role_id`	int	NOT NULL
-- );
--
--
-- CREATE TABLE `Posts` (
--                          `post_id`	int	NOT NULL primary key auto_increment,
--                          `reply_id`	int	NULL,
--                          `writer_id`	int	NOT NULL,
--                          `modifier_id`	int NULL,
--                          `title`	nvarchar(30)	NOT NULL,
--                          `content`	nvarchar(300)	NOT NULL,
--                          `created_at`	datetime	NOT NULL,
--                          `modified_at`	datetime	NULL,
--                          `deleteflag`	int NULL DEFAULT 1
-- );
--
--
--
-- CREATE TABLE `Comments` (
--                             `comment_id`	int	NOT NULL primary key auto_increment,
--                             `post_id`	int	NOT NULL,
--                             `writer_id`	int	NOT NULL,
--                             `content`	nvarchar(100)	NOT NULL,
--                             `created_at`	datetime	NOT NULL
-- );
--
--
--
-- CREATE TABLE `UserDivision` (
--                                 `userdivision_id`	int	NOT NULL primary key,
--                                 `role`	nvarchar(5)	NOT NULL
-- );
--
--
--
-- CREATE TABLE `Likes` (
--                          `user_id`	int	NOT NULL ,
--                          `post_id`	int	NOT NULL ,
--                          `like`	boolean	NOT NULL,
--                          constraint multi_key primary key (user_id, post_id)
-- );
--
-- alter table Users
--     add foreign key role_id
--     references UserDivision userdivision_id;
-- alter table Posts
--     add foreign key reply_id
--     references Posts post_id;
-- alter table Posts
--     add foreign key writer_id
--     references Users user_id;
-- alter table Posts
--     add foreign key modifier_id
--     references Users user_id;
-- alter table Comments
--     add foreign key post_id
--     references Posts post_id;
-- alter table Comments
--     add foreign key writer_id
--     references Users user_id;
-- alter table Likes
--     add foreign key user_id
--     references Users user_id;
-- alter table Likes
--     add foreign key post_id
--     references Posts post_id;
--
--
-- merge into UserDivision (userdivision_id, role)
--     values (1, 'admin');
-- insert into UserDivision (userdivision_id, role)
-- values (2, 'user');
--
-- merge into Users (name, password, created_at, role_id)
--     values ('leee', '123', now(), 2);
-- merge into Users (name, password, created_at, role_id)
--     values ('admin', '12345', now(), 1);
--
-- merge into Posts (reply_id, writer_id, title, content, created_at)
--     values (1, 1, '????????? ??????????????????.', '??????,,,', now());
--
-- merge into comments (post_id, writer_id, content, created_at)
--     values (1, 1, 'hi', now());
-- merge into comments (post_id, writer_id, content, created_at)
--     values (1, 1, '????????? ???????????????.', now());
--
-- merge into Likes values(1,1,true);
-- merge into Likes values(2,1,true);