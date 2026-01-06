package in.org.cris.cmm.sdms.service;

import in.org.cris.cmm.sdms.entity.MasterTable;

import java.util.List;

public interface MasterTableService {

	List<MasterTable> getByType(String type);
}
