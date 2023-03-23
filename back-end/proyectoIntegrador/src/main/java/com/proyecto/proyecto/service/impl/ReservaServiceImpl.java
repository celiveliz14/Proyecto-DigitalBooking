package com.proyecto.proyecto.service.impl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.proyecto.model.Cliente;
import com.proyecto.proyecto.repository.ClienteRepository;
import com.proyecto.proyecto.repository.UsersRepository;
import com.proyecto.proyecto.exception.BadRequestException;
import com.proyecto.proyecto.exception.ResourceNotFoundException;
import com.proyecto.proyecto.model.DTO.ReservaDTO;
import com.proyecto.proyecto.model.Reserva;
import com.proyecto.proyecto.repository.ReservaRepository;
import com.proyecto.proyecto.service.ReservaService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ObjectMapper mapper;
    private final UsersRepository userRepository;
    private final ClienteRepository clienteRepository;

    private final EmailSenderServiceImpl emailSenderService;
    private static final Logger LOGGER = Logger.getLogger(ReservaServiceImpl.class);


    @Override
    public ReservaDTO agregarReserva(ReservaDTO reservaDTO) throws BadRequestException, ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de guardado de la Reserva a la hora: " + reservaDTO.getHoraReserva());
        Reserva reserva = mapper.convertValue(reservaDTO, Reserva.class );
        if (reserva == null){
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
        reserva.setHoraReserva((timeNow()));
        reservaRepository.save(reserva);
        var userReserved = userRepository.findById(reservaDTO.getUser().getId()).get();
        emailSenderService.sendEmail(
                userReserved.getEmail()
                ,"Reserva creada exitosamente!!"
                ,userReserved.getFirst_name() + " " + userReserved.getLast_name() +
                        " Digital Booking te felicita por haber reservado de: " +
                        reservaDTO.getFechaInicio() + " a " + reservaDTO.getFechaFinal());
        ReservaDTO reservaReturn = mapper.convertValue(reserva,ReservaDTO.class);
        var searchUser = userRepository.findById(reservaDTO.getUser().getId());
        var searchCliente = clienteRepository.findByEmail(searchUser.get().getEmail());
        var clientToSave = mapper.convertValue(searchUser, Cliente.class);
        if (searchCliente.isEmpty()){
            clientToSave.setFechaCliente(dateNow());
            clienteRepository.save(clientToSave);
            return reservaReturn;
        }else{
            return reservaReturn;
        }
    }
    @Override
    public ReservaDTO actualizarReserva(ReservaDTO reservaDTO) throws BadRequestException{
        LOGGER.info("Se inicio una operacion de actualizado de Reserva con ID= " + reservaDTO.getId());
        Reserva reserva = mapper.convertValue(reservaDTO, Reserva.class);
        Optional<Reserva> reservaBuscada = reservaRepository.findById(reserva.getId());
        if (reservaBuscada.isPresent()){
            Reserva reservaUpdated = reservaRepository.save(reserva);
            ReservaDTO reservaReturn = mapper.convertValue(reservaUpdated,ReservaDTO.class);
            return reservaReturn;
        } else {
            throw new BadRequestException("Revise la informacion enviada por favor");
        }
    }
    @Override
    public Optional<ReservaDTO> buscarReserva(Long id) throws  ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de busqueda de Reserva con ID " + id);
        Optional<Reserva> reservaBuscada = reservaRepository.findById(id);
        if (reservaBuscada.isPresent()){
            ReservaDTO reservaDTO= mapper.convertValue(reservaBuscada.get(),ReservaDTO.class);
            return Optional.of(reservaDTO);
        }else{
            throw new ResourceNotFoundException("No se pudo encontrar la Reserva con ID: " + id);
        }
    }
    @Override
    public List<ReservaDTO> listaReserva() throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de Reservaes ");
        List<Reserva> reservaList = reservaRepository.findAll();
        List<ReservaDTO> listReturn = new ArrayList<>();
        if (reservaList.isEmpty()){
            throw new ResourceNotFoundException("La lista de Reservaes se encuentra vacia");
        }
        for (Reserva i:reservaList) {
            ReservaDTO reservaDTO = mapper.convertValue(i, ReservaDTO.class);
            listReturn.add(reservaDTO);
        }
        return listReturn;
    }

    @Override
    public void eliminarReserva(Long id) throws ResourceNotFoundException {
        Optional<ReservaDTO> reservaAEliminar = buscarReserva(id);
        if (reservaAEliminar.isPresent()){
            reservaRepository.deleteById(id);
            LOGGER.warn("Se realizo una operacion de eliminado de Reserva con id " + id);
        }
        else {
            throw new ResourceNotFoundException("La Reserva a eliminar no existe" +
                    " en la base de datos, se intentó encontrar sin éxito el id= "+id);
        }
    }

    @Override
    public Optional<List<ReservaDTO>> findAllByProductoId(Long id) throws ResourceNotFoundException {
        LOGGER.info("Se inicio una operacion de listado de reservas x producto Id: " + id);
        Optional<List<Reserva>> reservaList = reservaRepository.findAllByProductoId(id);
        List<ReservaDTO> listReturn = new ArrayList<>();
        if (reservaList.isEmpty()){
            throw new ResourceNotFoundException("La lista de productos se encuentra vacia");
        }
        for (Reserva r:reservaList.get()) {
            ReservaDTO reservaDTO = mapper.convertValue(r, ReservaDTO.class);
            listReturn.add(reservaDTO);
        }
        return Optional.of(listReturn);
    }


    public String timeNow(){
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        var hora = time.format(formatter);
        return hora;
    }

    public String dateNow(){
        LocalDate dateObj = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String date = dateObj.format(formatter);
        return date;
    }

}
