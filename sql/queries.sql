INSERT into cd.facilities
(facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
values (9, 'Spa', 20, 30, 100000, 800);

INSERT into cd.facilities
    (facid, name, membercost, guestcost, initialoutlay, monthlymaintenance)
    SELECT (SELECT max(facid) from cd.facilities)+1, 'Spa', 20, 30, 100000, 800;

UPDATE cd.facilities set initialoutlay = 10000 where facid = 1;

UPDATE cd.facilities facs
	SET membercost = facs2.membercost * 1.1,
	guestcost = facs2.guestcost * 1.1
	FROM (SELECT * FROM cd.facilities WHERE facid = 0) facs2
	WHERE facs.facid = 1;

DELETE from cd.bookings;

DELETE from cd.members WHERE memid = 37;

-- BASICS
SELECT facid, name, membercost, monthlymaintenance from cd.facilities
	WHERE membercost > 0 and (membercost < monthlymaintenance/50);

SELECT * from cd.facilities WHERE name like '%Tennis%';

SELECT * from cd.facilities WHERE facid IN (1,5);

SELECT memid, surname, firstname, joindate FROM cd.members
	WHERE joindate >= '2012-09-01';

SELECT surname FROM cd.members UNION SELECT name from cd.facilities;

-- JOINS

SELECT bookings.starttime FROM cd.bookings bookings
	INNER JOIN cd.members members
	on members.memid = bookings.memid
	WHERE
	members.firstname='David'
	AND members.surname='Farrell'; 

SELECT bks.starttime AS start, facs.name AS name
	FROM 
		cd.facilities facs
		INNER JOIN cd.bookings bks
			ON facs.facid = bks.facid
	WHERE 
		facs.name IN ('Tennis Court 2','Tennis Court 1') AND
		bks.starttime >= '2012-09-21' AND
		bks.starttime < '2012-09-22'
ORDER BY bks.starttime; 

SELECT mems.firstname AS memfname, mems.surname AS memsname, recs.firstname AS recfname, recs.surname AS recsname
	from 
		cd.members mems
		LEFT OUTER JOIN cd.members recs
			ON recs.memid = mems.recommendedby
ORDER BY memsname, memfname; 

SELECT DISTINCT recs.firstname AS fname, recs.surname AS sname
	FROM 
		cd.members mems
		INNER JOIN cd.members recs
			ON recs.memid = mems.recommendedby
ORDER BY sname, fname;

SELECT DISTINCT mems.firstname || ' ' || mems.surname AS member,
	(SELECT recs.firstname || ' ' || recs.surname AS recommender
	FROM cd.members recs
	WHERE recs.memid = mems.recommendedby
	)
	FROM
	cd.members mems
ORDER BY member;
