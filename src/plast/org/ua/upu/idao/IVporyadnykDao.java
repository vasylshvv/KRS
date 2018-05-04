package plast.org.ua.upu.idao;

import java.util.List;

import plast.org.ua.upu.pojo.VporyadnykPojo;
import plast.org.ua.upu.pojo.VporyadnykSamurtokPojo;
import plast.org.ua.upu.table.Vporyadnyk;

public interface IVporyadnykDao {
	public void addVporyadnyk(Vporyadnyk  vporyadnyk);
	public void update(Vporyadnyk vporyadnyk);
	public List<VporyadnykPojo> findAllVporyadnyk(Long idkurin);
	public List<VporyadnykSamurtokPojo> findVporyadnykSamHurtok(Long idsamhurtok);
	public List<Vporyadnyk> findVporyadnykForHurtok(Long idhurtok, Long idkurin);
	public List<Vporyadnyk> findVporyadnykForHurtok(Long idhurtok, Long idkurin, Long idperson);
	public List<Vporyadnyk> findVporyadnykForSamHurtok(Long idsamhurtok);
	public List<Vporyadnyk> findVporyadnykForSamHurtok(Long idsamhurtok, Long idperson);
}