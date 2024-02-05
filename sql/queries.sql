INSERT into cd.facilities
(facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
values (9, 'Spa', 20, 30, 100000, 800);

INSERT into cd.facilities
    (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
    SELECT (SELECT max(facid) from cd.facilities)+1, 'Spa', 20, 30, 100000, 800;

UPDATE cd.facilities set initialoutlay = 10000 where facid = 1; 
