package br.com.send.service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.send.enums.PerfilEnum;
import br.com.send.model.DispositivoModel;
import br.com.send.model.EmpresaModel;
import br.com.send.model.UsuarioModel;
import br.com.send.repository.EmpresaRepository;
import br.com.send.repository.UsuarioRepository;
import br.com.send.util.DataUtil;
import br.com.send.vo.EmpresaVo;
import br.com.send.vo.ImagemVo;
import br.com.send.vo.ResponseVo;
import javassist.NotFoundException;

@Service
public class EmpresaService {

	private static final Logger logger = LogManager.getLogger(EmpresaService.class);
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private ImagemService imagemService;
	
	public List<EmpresaVo> findAll() throws Exception{
		
		try {
			return empresaRepository.findAll()
					.parallelStream().filter( t -> Boolean.TRUE.equals( t.getAtivo() ))
					.map(  EmpresaVo :: new ).collect(Collectors.toList());
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public EmpresaVo find(Long id) throws Exception{
		try {
			return new EmpresaVo( empresaRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Não existe empresa para "+id)  ));
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	
	
	public List<EmpresaVo> findUsuario(Long idUsuario) throws Exception{
		try {
			UsuarioModel usuario = usuarioRepository.findById(idUsuario)
				.orElseThrow(() -> new NotFoundException("Não existe empresa para "+ idUsuario)  );
			
			if( usuario.getPerfil() != null && !PerfilEnum.ADMIN.getDescricao().equals( usuario.getPerfil().getDescricao() ) ) {
				return Collections.singletonList(new EmpresaVo( usuario.getEmpresa() ));
			}
			
			return this.findAll();
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo atualiza(EmpresaVo empresaVo) throws Exception{
		
		EmpresaModel empresa = null;
		
		try {
			
			empresa = empresaRepository.findById(empresaVo.getIdEmpresa())
		 			.orElseThrow(() -> new NotFoundException("Não existe empresa para "+empresaVo.getIdEmpresa()));
		 	
			empresa.setCnpj(empresaVo.getCnpj());
			empresa.setDtModificada(DataUtil.getDataAtual());
			empresa.setEmailContato(empresaVo.getEmailContato());
			empresa.setTelefoneContato(empresaVo.getTelefoneContato());
			empresa.setRazaoSocial(empresaVo.getRazaoSocial());

			empresaRepository.saveAndFlush( empresa );

		 	return new ResponseVo("Empresa atualizada");
		 	
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	
	public ResponseVo geraEmpresaInicial() throws Exception{
		
		try {
			
			EmpresaModel empresaModel = new EmpresaModel();
			
			empresaModel.setAtivo(Boolean.TRUE);
			empresaModel.setCnpj("00.000.000/0001-00");
			empresaModel.setDtCadastro(DataUtil.getDataAtual());
			empresaModel.setEmailContato("admin@sendsolutions.me");
			empresaModel.setRazaoSocial("Send Solutions");
			empresaModel.setTelefoneContato("(11)99999-9999");
			
			usuarioService.geraAdminInicial( empresaRepository.saveAndFlush( empresaModel ) );
			
			return new ResponseVo("Carga inicial da empresa salva");
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo salva(EmpresaVo empresaVo) throws Exception{
		
		try {
			
			if(empresaVo.getIdEmpresa() != null) {
				return this.atualiza(empresaVo);
			}else {
				
				EmpresaModel empresaModel = empresaRepository.findByCnpjAndAtivoFalse(empresaVo.getCnpj());
				
				if( empresaModel != null ) {
					empresaModel.setAtivo(Boolean.TRUE);
					empresaModel.setDtCadastro( DataUtil.getDataAtual() );
				}else {
					empresaModel = new EmpresaModel( empresaVo);
				}
				
				empresaRepository.saveAndFlush( empresaModel );
				return new ResponseVo("Empresa salvo");
			}
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo desativar(Long id) throws Exception{
		
		try {
			EmpresaModel empresa = empresaRepository.findById(id)
					.orElseThrow(() -> new NotFoundException("Não existe empresa para "+id) );
			
			empresa.setDtModificada(DataUtil.getDataAtual());
			empresa.setAtivo(Boolean.FALSE);
			empresaRepository.saveAndFlush(empresa);
			
			return new ResponseVo("Dados atualizado com sucesso");
			
		} catch (NotFoundException e) {
			logger.error("{}", e);
			throw e;
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
	}
	
	public ResponseVo upload(Long idEmpresa,  MultipartFile fotoEmpresa) throws Exception{
		
		try {
			
            EmpresaModel empresa = empresaRepository.findById(idEmpresa)
					.orElseThrow(() -> new NotFoundException("Não existe empresa para "+idEmpresa) );
			
			empresa.setDtModificada(DataUtil.getDataAtual());
			
			imagemService.salvar(fotoEmpresa.getBytes() , fotoEmpresa.getName());
			
			empresaRepository.saveAndFlush(empresa);

			return new ResponseVo("Foto do perfil salva");
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
		
	}
	
	public ImagemVo download(Long idEmpresa) throws Exception{
		
		try {
			
            EmpresaModel empresa = empresaRepository.findById(idEmpresa)
					.orElseThrow(() -> new NotFoundException("Não existe empresa para "+idEmpresa) );
			
            ImagemVo imagemVo = new ImagemVo();
            
            if( empresa.getImagem() != null ) {
            	
            	imagemVo.setConteudo("data:image/jpeg;base64," + new String(empresa.getImagem()));
            }
            
			return imagemVo;
			
		}catch (Exception e) {
			logger.error("{}", e);
			throw e;
		}
		
	}
	
}
