CREATE OR REPLACE FUNCTION M09_GETDONECHALLENGEBYID(code int)
  RETURNS TABLE (id int,
                 name varchar(20),
                 description varchar(30),
                 score int)
AS $$
BEGIN
  RETURN QUERY
  SELECT C.challengeid,
    C.challengename,
    C.challengedescription,
    C.challengescore
  FROM CHALLENGE C, DETAIL D
  WHERE D.FK_CHALLENGEID = C.CHALLENGEID
    AND D.FK_USERID = code
    AND D.DETAILACTIVE = TRUE;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION M09_GETCHALLENGEPOINTBYID(code int)
  RETURNS integer
AS $$
BEGIN
  SELECT COUNT(*)
  FROM CHALLENGE C, DETAIL D
  WHERE D.FK_CHALLENGEID = C.CHALLENGEID
        AND D.FK_USERID = code
        AND D.DETAILACTIVE = TRUE;
END;
$$ LANGUAGE plpgsql;

