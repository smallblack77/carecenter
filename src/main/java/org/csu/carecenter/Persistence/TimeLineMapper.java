package org.csu.carecenter.Persistence;

import org.csu.carecenter.entity.TimeLine;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TimeLineMapper {

    List<TimeLine> getAllTimeLine();

    List<TimeLine> getTimeLineById(int custId, String day);

    void insertTimeLime(TimeLine timeLine);

    List<String> getDayList(int custId);
}
