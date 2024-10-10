package com.test.toy.board.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import com.test.toy.board.model.BoardDTO;
import com.test.util.DBUtil;

public class BoardDAO {

	private static BoardDAO dao;

	private Connection conn;
	private Statement stat;
	private PreparedStatement pstat;
	private ResultSet rs;

	private BoardDAO() {
		this.conn = DBUtil.open("localhost", "toy", "java1234");
	}

	public static BoardDAO getInstance() {

		if (dao == null) {

			dao = new BoardDAO();

		}

		return dao;

	}

	// - 글쓰기
	// - 목록보기
	// - 상세보기(글 1개)
	// - 조회수 증가하기
	// - 수정하기
	// - 삭제하기

	// - 글쓰기
	public int add(BoardDTO dto) {

		// queryParamNoReturn
		try {

			String sql = "insert into tblBoard (seq, subject, content, regdate, readcount, id) values (seqBoard.nextVal, ?, ?, default, default, ?)";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getId());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return 0;
	}

	// - 목록보기
	public ArrayList<BoardDTO> list(HashMap<String, String> map) {
		
		//queryNoParamListReturn
		try {
			//목록: select * from vwBoard
			//검색: select * from vwBoard where 조건
			
			String where = "";
			
			if (map.get("search").equals("y")) {
				//where subject like '%검색어%'
				//where content like '%검색어%'
				//where name like '%검색어%'
				//all일 때
				//- where subject like '%검색어%' or content like '%검색어%' or name like '%검색어%'
				if(!map.get("column").equals("all")) {
					
					where = String.format("where %s like '%%%s%%'"
							,map.get("column")
							,map.get("word"));
					
				} else {
					
			
			where = String.format("where subject like '%%%s%%' or content like '%%%s%%' or name like '%%%s%%'"
															,map.get("word")
															,map.get("word")
															,map.get("word"));
				}
			}			
			
			
			
			String sql = String.format("select * from vwBoard %s", where);
			
			
			stat = conn.createStatement();
			rs = stat.executeQuery(sql);
			
			ArrayList<BoardDTO> list = new ArrayList<BoardDTO>();
			
			while (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setId(rs.getString("id"));
				
				dto.setRegtime(rs.getString("regtime"));
				dto.setName(rs.getString("name"));
				dto.setIsnew(rs.getDouble("isnew"));
				
				list.add(dto);				
			}	
			
			return list;
			
		}catch(

	Exception e)
	{
			e.printStackTrace();
		}

	return null;
	}

	// - 상세보기(글 1개)
	public BoardDTO get(String seq) {
		
		//queryParamDTOReturn
		try {
			
			String sql = "select tblBoard.*, (select name from tblUser where id = tblBoard.id) as name from tblBoard where seq = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				BoardDTO dto = new BoardDTO();
				
				dto.setSeq(rs.getString("seq"));
				dto.setSubject(rs.getString("subject"));
				dto.setRegdate(rs.getString("regdate"));
				dto.setReadcount(rs.getInt("readcount"));
				dto.setId(rs.getString("id"));
				dto.setContent(rs.getString("content"));
				
				dto.setName(rs.getString("name"));
				
				return dto;				
			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

	// - 조회수 증가하기
	public void updateReadcount(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblBoard set readcount = readcount + 1 where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// - 수정하기
	public int edit(BoardDTO dto) {
		
		//queryParamNoReturn
		try {

			String sql = "update tblBoard set subject =?, content = ? where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getSubject());
			pstat.setString(2, dto.getContent());
			pstat.setString(3, dto.getSeq());

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		
		return 0;
	}

	// - 삭제하기
	public int del(String seq) {
		
		//queryParamNoReturn
		try {

			String sql = "delete from tblBoard where seq = ?";

			pstat = conn.prepareStatement(sql);
			pstat.setString(1, seq);

			return pstat.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}

		
		return 0;
	}

}
