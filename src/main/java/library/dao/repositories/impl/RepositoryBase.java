package library.dao.repositories.impl;

import library.dao.mappers.IMapper;
import library.dao.repositories.IRepository;
import library.dao.uow.Entity;
import library.dao.uow.IUnitOfWork;
import library.dao.uow.IUnitOfWorkRepository;
import library.domain.IHaveId;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public abstract class RepositoryBase <TEntity extends IHaveId>
    implements IRepository<TEntity>, IUnitOfWorkRepository {

    protected abstract String createTableSql();
    protected abstract String getTableName();
    protected abstract String getInsertSql();
    protected abstract String getUpdateSql();
    protected abstract void setInsert(TEntity entity) throws SQLException;
    protected abstract void setUpdate(TEntity entity) throws SQLException;

    protected Connection _connection;
    protected boolean tableExists;
    protected PreparedStatement insert;
    protected PreparedStatement selectById;
    protected PreparedStatement lastId;
    protected PreparedStatement selectByPage;
    protected PreparedStatement count;
    protected PreparedStatement delete;
    protected PreparedStatement update;
    protected IMapper<TEntity> _mapper;
    protected IUnitOfWork _uow;

    protected RepositoryBase(Connection connection, IMapper<TEntity> mapper, IUnitOfWork uow) throws SQLException{

        _mapper = mapper;
        _connection = connection;
        _uow = uow;
        checkIfTableExists(connection);
        if(!tableExists){
            createTable();
            _uow.saveChanges();
        }
        initStatements(connection);
    }

    private void checkIfTableExists(Connection connection) throws SQLException {
        ResultSet rs = connection.getMetaData().getTables(null, null, null, null);

        while(rs.next()){
            if(rs.getString("TABLE_NAME").equalsIgnoreCase(getTableName())){
                tableExists = true;
                break;
            }
        }
    }

    private void initStatements(Connection connection) throws SQLException {
        insert = connection.prepareStatement(getInsertSql());

        selectById = connection.prepareStatement( "SELECT * FROM "+ getTableName()+ " WHERE id=?");

        lastId = connection.prepareStatement(""
                + "SELECT MAX(id) FROM "
                + getTableName());

        count = connection.prepareStatement(""
                + "SELECT COUNT(*) FROM "
                + getTableName());

        selectByPage = connection.prepareStatement(""
                + "SELECT * FROM "
                + getTableName()
                + " OFFSET ? LIMIT ?"
                + "");

        delete = connection.prepareStatement(""
                + "DELETE FROM "
                + getTableName()
                + " WHERE id=?");

        update = connection.prepareStatement(getUpdateSql());
    }

    public void delete(TEntity entity){

        Entity ent = new Entity(this);
        ent.setEntity(entity);
        _uow.markAsDeleted(ent);
    }

    public int count(){

        try {
            ResultSet rs = count.executeQuery();
            while(rs.next())
                return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int lastId(){

        try {
            ResultSet rs = lastId.executeQuery();
            while(rs.next())
                return rs.getInt(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void add(TEntity entity){
        Entity ent = new Entity(this);
        ent.setEntity(entity);
        _uow.markAsNew(ent);
    }

    public void update(TEntity entity){

        Entity ent = new Entity(this);
        ent.setEntity(entity);
        _uow.markAsChanged(ent);
    }

    public void createTable(){

        String createTableSql = createTableSql();

        try {
            Statement createTable = _connection.createStatement();
            if(!tableExists)
                createTable.executeUpdate(createTableSql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<TEntity> getPage(int offset, int limit){

        List<TEntity> result = new ArrayList<TEntity>();
        try {
            selectByPage.setInt(1, offset);
            selectByPage.setInt(2, limit);
            ResultSet rs = selectByPage.executeQuery();
            while(rs.next()){
                result.add(_mapper.map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public TEntity get(int id){
        try {
            selectById.setInt(1, id);
            ResultSet rs = selectById.executeQuery();
            while(rs.next()){
                return _mapper.map(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void persistAdd(Entity entity){

        try {
            setInsert((TEntity)entity.getEntity());
            insert.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void persistUpdate(Entity entity){
        try {
            setUpdate((TEntity)entity.getEntity());
            update.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void persistDelete(Entity entity){
        try {
            delete.setInt(1, entity.getEntity().getId());
            delete.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }}

}
