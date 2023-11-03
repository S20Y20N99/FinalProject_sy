--ST_NUM으로 해당 RV_NUM 을 가지고 있는 리뷰가 REPLY_NUM을 가지고 있을 떄 REPLY_CONTENT 와 REG_DATE를 뿌려준다.

SELECT RR.REPLY_NUM AS REPLY_NUM, RB.RV_NUM AS RV_NUM
     , RR.REPLY_CONTENT AS REPLAY_CONTENT, RR.REG_DATE AS REG_DATE
FROM RV_REPLY RR LEFT JOIN RV_BOX RB
   ON RR.RV_NUM = RB.RV_NUM
WHERE RB.ST_NUM = 1
  and rr.rv_num = 6;

SELECT DISTINCT(RB.RV_NUM) AS RV_NUM
		FROM RV_REPLY RR LEFT JOIN RV_BOX RB
		  ON RR.RV_NUM = RB.RV_NUM
		WHERE RB.ST_NUM = 1;

SELECT *
FROM PEN_TYPE_LABEL;

INSERT INTO REQ_APPLY(req_apply_num, USEr_NUM, REG_DATE, ST_CHBOX_NUM, REQ_RS)
VALUES(10, 11, DEFAULT, 1, '화장실이 없어요');

INSERT INTO REQ_PROCESS(REQ_PROCESS_NUM, REQ_APPLY_NUM, ADMIN_NUM, FINAL_DATE)
VALUES(7, 10, 2, DEFAULT);

INSERT INTO PENALTY(PEN_GRANT_NUM, REQ_PROCESS_NUM, GRANT_DATE, PEN_TYPE_NUM)
VALUES(3, 7, SYSDATE, 1);

SELECT *
FROM 
(
SELECT *
FROM PENALTY P
WHERE SYSDATE - GRANT_DATE < 3
  AND 0 < SYSDATE - GRANT_DATE
)T 
WHERE T.PEN_GRANT_NUM NOT IN (SELECT PEN_GRANT_NUM
                              FROM REVO_APPLY);
SELECT
FROM ST