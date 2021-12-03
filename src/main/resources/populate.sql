DELETE FROM vger_support.alma_counts;
COMMIT;
INSERT INTO vger_support.alma_counts(loc,chromebooks_in,mac_laptops_in,win_laptops_in,ipads_in) VALUES ('Powell',5,10,15,20);
INSERT INTO vger_support.alma_counts(loc,chromebooks_in,mac_laptops_in,win_laptops_in,ipads_in) VALUES ('YRL',10,0,5,2);
COMMIT;