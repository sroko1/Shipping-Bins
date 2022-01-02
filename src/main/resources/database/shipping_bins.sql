CREATE TABLE IF NOT EXISTS BIN(
                                  ID smallint not null auto_increment,
                                  NAME varchar (10) not null,
    WIDTH smallint not null,
    LENGTH smallint not null,
    HEIGHT smallint not null,
    PRICE double not null,
    AMOUNT smallint not null,
    VOLUME double not null,
    LEASING_PRICE double not null,
    PRIMARY KEY (ID)
    );

CREATE TABLE IF NOT EXISTS TRUCK (
                                     ID smallint not null auto_increment,
                                     TRAILER_TYPE varchar (10) not null,
    REG_NUMBER varchar (15) not null,
    TRAILER_MAX_VOLUME smallint unsigned not null,
    primary key(ID)
    );

CREATE TABLE IF NOT EXISTS SUPPLIER (
                                        ID SMALLINT NOT NULL AUTO_INCREMENT,
                                        NAME VARCHAR(45) NOT NULL,
    PRIMARY KEY (ID)
    );

CREATE TABLE IF NOT EXISTS INBOUND(
                                      ID smallint not null auto_increment,
                                      INCOMING_TYPE varchar (15)  null,
    QUANTITY smallint  null,
    LOCATION varchar(10)  null,
    ARRIVAL date  null,
    BIN_ID smallint   null,
    TRUCK_ID smallint null,
    SUPPLIER_ID smallint null,
    PRIMARY KEY (ID),
    FOREIGN KEY (BIN_ID) references BIN(ID) on delete restrict,
    FOREIGN KEY (TRUCK_ID) references TRUCK(ID) on delete restrict,
    FOREIGN KEY (SUPPLIER_ID) references SUPPLIER(ID) on delete restrict
    );

CREATE TABLE IF NOT EXISTS OUTBOUND(
                                       ID smallint not null auto_increment,
                                       OUTCOMING_TYPE varchar (15) not null,
    QUANTITY smallint  not null,
    LOCATION varchar(10) not null,
    DEPARTURE date not null,
    BIN_ID smallint  not null,
    TRUCK_ID smallint  not null,
    SUPPLIER_ID smallint  not null,
    PRIMARY KEY (ID),
    FOREIGN KEY (BIN_ID) references BIN(ID) on delete restrict,
    FOREIGN KEY (TRUCK_ID) references TRUCK(ID) on delete restrict,
    FOREIGN KEY (SUPPLIER_ID) references SUPPLIER(ID) on delete restrict
    );

INSERT INTO BIN VALUES
(1,'AB1234', 594, 396, 280, 9.37, 200, 10, 100),
(2,'AB2345', 1240, 835, 966, 91.69, 200, 10, 100),
(3,'AB3456', 1240, 835, 966, 116.93, 200, 10, 100),
(4,'AB4567', 3020, 1300, 970, 381.17, 200, 10, 100),
(5,'AB5678', 3600, 1200, 970, 427.53, 200, 10, 100),
(6,'AB6789', 500, 1200, 245, 40.18, 200, 10, 100),
(7,'AB7890', 926, 866, 600, 81.39, 200, 10, 100),
(8,'BC1234', 926, 866, 880, 99.93, 200, 10, 100),
(9,'BC2345', 800, 700, 770, 61.30, 200, 10, 100),
(10,'BC3456', 926, 866, 300, 47.39, 200, 10, 100);

INSERT INTO TRUCK VALUES
(1, 'MEGA','DLU X1098', 13600),
(2, 'NORMAL', 'DW 30456', 13600),
(3, 'TANDEM', 'FZI 45678', 15400),
(4, 'MEGA', 'PZ 250LT', 15400),
(5, 'NORMAL', 'EL 4567T', 11400);

INSERT INTO SUPPLIER VALUES
('1', 'SUP-X'),
('2', 'SUP-Y'),
('3', 'SUP-Z'),
('4', 'SUP-H'),
('5', 'SUP-E');

insert into INBOUND values
(1,'HOMOGENEOUS', 45, 'DORTMUND', '2020-11-29', 1, 2, 3),
(2,'MIX', 78, 'BERGEN', '2009-11-10', 2, 4, 5),
(3,'WOODEN', 39, 'LUBLIN', '2018-03-04', 3, 1, 2),
(4,'MIX', 90, 'SPLIT', '2017-06-23', 5, 4, 3),
(5,'HOMOGENEOUS',65, 'PORTO', '2012-05-30', 2, 4, 1);
