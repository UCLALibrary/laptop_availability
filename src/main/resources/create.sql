CREATE SCHEMA IF NOT EXISTS vger_support;

CREATE TABLE IF NOT EXISTS vger_support.alma_counts
(
loc	        VARCHAR2(25),
chromebooks_in	INT,
mac_laptops_in	INT,
win_laptops_in	INT,
ipads_in	INT
);
