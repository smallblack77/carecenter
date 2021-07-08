package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.Out;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OutMapper {

    List<Out> getOutList(int custid);

    List<Out> getAllOutList();

    void insertOut(Out out);

    void deleteOut(int id);

    void updateOut(Out out);
}
