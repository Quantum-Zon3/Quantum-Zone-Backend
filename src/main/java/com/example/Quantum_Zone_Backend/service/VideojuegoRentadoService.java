/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.Quantum_Zone_Backend.service;

import com.example.Quantum_Zone_Backend.modelo.Cliente;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.Quantum_Zone_Backend.modelo.VideoJuego;
import com.example.Quantum_Zone_Backend.modelo.VideojuegoRentado;
import com.example.Quantum_Zone_Backend.repository.VideojuegosRentadosRepository;
import com.example.Quantum_Zone_Backend.service.ClienteService;
import com.example.Quantum_Zone_Backend.service.VideoJuegoService;

@Service
public class VideojuegoRentadoService {
	private VideojuegosRentadosRepository videojuegoRRepository;
	private VideojuegoRentadoService videojuegoRService;
	private VideoJuegoService videojuegoService;
	private ClienteService clienteService;

	@Autowired
	public VideojuegoRentadoService(VideojuegosRentadosRepository videojuegoRRepository,
			VideoJuegoService videojuegoService, ClienteService clienteService) {

		this.videojuegoRRepository = videojuegoRRepository;
		this.videojuegoService = videojuegoService;
		this.clienteService = clienteService;
	}

	
	// encontrar un puesto por id
	public VideojuegoRentado findById(String id) {
		return videojuegoRRepository.findById(id);
	}

	// listar todos los puestos
	public List<VideojuegoRentado> findAll() {
		return videojuegoRRepository.findAll();
	}

	// eliminar un puesto por id
	public void deleteById(String id) {
		videojuegoRRepository.deleteById(id);
	}

	// actualizar un puesto
	public VideojuegoRentado update(VideojuegoRentado videojuegoR) {
		return videojuegoRRepository.update(videojuegoR);
	}

	// buscar puesto por filtros
	public List<VideojuegoRentado> findByFilters(String id, String cedula, VideoJuego videojuego,
			LocalDate fechaDeRenta, LocalDate fechaDeDevolucion) {
		return videojuegoRRepository.findByFilters(id, fechaDeRenta, fechaDeDevolucion, cedula, videojuego);
	}

	public VideojuegoRentado save(VideojuegoRentado videojuegoR) {
		return videojuegoRRepository.save(videojuegoR);
	}

}
