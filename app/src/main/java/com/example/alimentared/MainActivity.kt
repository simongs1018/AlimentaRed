package com.example.alimentared

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.alimentared.ui.screens.*
import com.example.alimentared.ui.theme.AlimentaRedTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AlimentaRedTheme {
                var currentScreen by remember { mutableStateOf("login") }
                var selectedLoteId by remember { mutableStateOf<Long?>(null) }

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {

                        // Contenedor principal de pantallas
                        Box(modifier = Modifier.weight(1f)) {
                            when (currentScreen) {
                                "login" -> LoginScreen(
                                    onLoginClick = { _, _ -> currentScreen = "lotes" },
                                    onNavigateToRegister = { currentScreen = "lotes" }
                                )

                                "lotes" -> LotesScreen(
                                    onLoteClick = { id ->
                                        selectedLoteId = id
                                        currentScreen = "detalle_lote"
                                    }
                                )

                                "crear_lote" -> CrearLoteScreen(
                                    onGuardarLote = { _, _, _, _, _ -> currentScreen = "lotes" },
                                    onVolver = { currentScreen = "lotes" }
                                )

                                "detalle_lote" -> DetalleLoteScreen(
                                    tituloLote = "Panadería Fresca del Día",
                                    categoriaLote = "Panadería",
                                    cantidadDisponible = "15.0 kg",
                                    fechaExpiracion = "2026-10-28 12:00",
                                    donanteNombre = "Panadería La Española",
                                    onSolicitarReserva = { _, _ -> currentScreen = "mis_solicitudes" },
                                    onVolver = { currentScreen = "lotes" }
                                )

                                "mis_solicitudes" -> MisSolicitudesScreen(
                                    onVolver = { currentScreen = "lotes" }
                                )

                                "seguimiento" -> SeguimientoEntregaScreen(
                                    idSolicitud = 102,
                                    loteTitulo = "Panadería del Día (15.0 kg)",
                                    puntoRecoleccion = "Calle 10 # 5-22, Sede Donante",
                                    onConfirmarEntrega = { currentScreen = "indicadores" },
                                    onVolver = { currentScreen = "lotes" }
                                )

                                "indicadores" -> IndicadoresScreen(
                                    onVolver = { currentScreen = "lotes" }
                                )

                                "gestion_usuarios" -> GestionUsuariosScreen(
                                    onVolver = { currentScreen = "lotes" }
                                )

                                "gestion_logistica" -> GestionLogisticaScreen(
                                    onVolver = { currentScreen = "lotes" }
                                )
                            }
                        }

                        // Barra de navegación horizontal para probar fácilmente las 9 pantallas
                        if (currentScreen != "login") {
                            Surface(
                                tonalElevation = 8.dp,
                                color = MaterialTheme.colorScheme.surfaceVariant
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .horizontalScroll(rememberScrollState())
                                        .padding(horizontal = 4.dp, vertical = 8.dp),
                                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                                ) {
                                    FilterChip(
                                        selected = currentScreen == "lotes",
                                        onClick = { currentScreen = "lotes" },
                                        label = { Text("Lotes") }
                                    )
                                    FilterChip(
                                        selected = currentScreen == "crear_lote",
                                        onClick = { currentScreen = "crear_lote" },
                                        label = { Text("+ Donar") }
                                    )
                                    FilterChip(
                                        selected = currentScreen == "mis_solicitudes",
                                        onClick = { currentScreen = "mis_solicitudes" },
                                        label = { Text("Historial") }
                                    )
                                    FilterChip(
                                        selected = currentScreen == "seguimiento",
                                        onClick = { currentScreen = "seguimiento" },
                                        label = { Text("Entrega") }
                                    )
                                    FilterChip(
                                        selected = currentScreen == "indicadores",
                                        onClick = { currentScreen = "indicadores" },
                                        label = { Text("Métricas") }
                                    )
                                    FilterChip(
                                        selected = currentScreen == "gestion_usuarios",
                                        onClick = { currentScreen = "gestion_usuarios" },
                                        label = { Text("Admin Cuentas") }
                                    )
                                    FilterChip(
                                        selected = currentScreen == "gestion_logistica",
                                        onClick = { currentScreen = "gestion_logistica" },
                                        label = { Text("Coord. Logística") }
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}