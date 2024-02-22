CREATE TABLE board(
  board_id INT(11) NOT NULL AUTO_INCREMENT,
  content VARCHAR(1000) NOT NULL,
  title VARCHAR(255) NULL,
  created_date  DATETIME,
  last_modified_date  DATETIME,
  CONSTRAINT board_PK PRIMARY KEY(board_id)
);

insert into board (content, title, created_date) values ("testContent1" ,"testTitle1", now());
insert into board (content, title, created_date) values ("testContent2" ,"testTitle2", now());
insert into board (content, title, created_date) values ("testContent3" ,"testTitle3", now());
insert into board (content, title, created_date) values ("testContent4" ,"testTitle4", now());
insert into board (content, title, created_date) values ("testContent5" ,"testTitle5", now());
insert into board (content, title, created_date) values ("testContent6" ,"testTitle6", now());
insert into board (content, title, created_date) values ("testContent7" ,"testTitle7", now());
insert into board (content, title, created_date) values ("testContent8" ,"testTitle8", now());