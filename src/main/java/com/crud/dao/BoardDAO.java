package com.crud.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.crud.bean.BoardVO;
import com.crud.common.JDBCUtil;

public class BoardDAO {

	private static final String M_SELECT = "select photo from food_app where id=?";
	static Connection conn = null;
	static PreparedStatement stmt = null;
	static ResultSet rs = null;

	private final String BOARD_INSERT = "insert into food_app (name, address, foodName, flavor, takeOut, request, price) values (?,?,?,?,?,?,?)";
	private final String BOARD_UPDATE = "update food_app set name=?, address=?, foodName=?, flavor=?, takeOut=?, request=?, price=? where id=?";
	private final String BOARD_DELETE = "delete from food_app  where id=?";
	private static final String BOARD_GET = "select * from food_app  where id=?";
	private final String BOARD_LIST = "select * from food_app order by id desc";

	public int insertBoard(BoardVO vo) {
		System.out.println("===> JDBC로 insertBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getAddress());
			stmt.setString(3, vo.getFoodName());
			stmt.setString(4, vo.getFlavor());
			stmt.setInt(5, vo.getTakeOut());
			stmt.setString(6, vo.getRequest());
			stmt.setInt(7, vo.getPrice());
			stmt.executeUpdate();
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	// 글 삭제
	public void deleteBoard(BoardVO vo) {
		System.out.println("===> JDBC로 deleteBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public int updateBoard(BoardVO vo) {
		System.out.println("===> JDBC로 updateBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getName());
			stmt.setString(2, vo.getAddress());
			stmt.setString(3, vo.getFoodName());
			stmt.setString(4, vo.getFlavor());
			stmt.setInt(5, vo.getTakeOut());
			stmt.setString(6, vo.getRequest());
			stmt.setInt(7, vo.getPrice());
			stmt.setInt(8, vo.getId());
			
			
			System.out.println(vo.getName() + "-" + vo.getAddress() + "-" + vo.getFoodName() + "-" + vo.getFlavor() + "-" + vo.getTakeOut() + "-" + vo.getRequest() + "-" + vo.getPrice() + "-" + vo.getId());
			stmt.executeUpdate();
			return 1;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}	
	
	public static BoardVO getBoard(int seq) {
		BoardVO one = new BoardVO();
		System.out.println("===> JDBC로 getBoard() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, seq);
			rs = stmt.executeQuery();
			if(rs.next()) {
				one.setId(rs.getInt("id"));
				one.setName(rs.getString("name"));
				one.setAddress(rs.getString("address"));
				one.setFoodName(rs.getString("foodName"));
				one.setFlavor(rs.getString("flavor"));
				one.setTakeOut(rs.getInt("takeOut"));
				one.setRequest(rs.getString("request"));
				one.setRegdate(rs.getDate("regdate"));
				one.setPrice(rs.getInt("price"));
				one.setCnt(rs.getInt("cnt"));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return one;
	}
	
	public List<BoardVO> getBoardList(){
		List<BoardVO> list = new ArrayList<BoardVO>();
		System.out.println("===> JDBC로 getBoardList() 기능 처리");
		try {
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			while(rs.next()) {
				BoardVO one = new BoardVO();
				one.setId(rs.getInt("id"));
				one.setName(rs.getString("name"));
				one.setAddress(rs.getString("address"));
				one.setFoodName(rs.getString("foodName"));
				one.setFlavor(rs.getString("flavor"));
				one.setTakeOut(rs.getInt("takeOut"));
				one.setRequest(rs.getString("request"));
				one.setRegdate(rs.getDate("regdate"));
				one.setPrice(rs.getInt("price"));
				one.setCnt(rs.getInt("cnt"));
				list.add(one);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return list;
	}

	public String getPhotoFilename(int id){
		String filename = null;
		try{
			conn = JDBCUtil.getConnection();
			stmt = conn.prepareStatement(M_SELECT);
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs.next()){
				filename = rs.getString("photo");
			}
			rs.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("===> JDBC로 getPhotoFilename() 기능 처리");
		return filename;
	}
}
