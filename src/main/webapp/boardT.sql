create table boardT(
    num NUMBER(5) primary key not NULL,
    writer VARCHAR2(10) not NULL,
    title varchar2(30) not NULL,
    content VARCHAR2(100),
    writeday DATE DEFAULT sysdate not NULL,
    Readcnt number(4) default 0,
    repRoot number(5),
    repStep number(5),
    repIndent number(5));
    
    create sequence boardT_seq;
    
    insert into boardT(num,writer,title,content, Readcnt, repRoot, repStep, repIndent ) values (boardT_seq.nextval,'작성자','테스트','테스트파일',boardT_seq.CURRVAL,0,0);
    