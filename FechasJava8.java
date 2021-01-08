/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javatime;

// Clase para tratar fechas (sólo día, mes y año)
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
// Enumerado para los meses del año (Enero, ...)
import java.time.Month;
import java.time.MonthDay;
import java.time.Period;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 *
 * @author usuario
 */
public class FechasJava8 {
    
    public static void main(String[] args) {
        
        /* CREACIÓN DE FECHAS Y OBTENCIÓN DE CAMPOS*/
        
        // Fecha actual
        LocalDate hoy = LocalDate.now();
        System.out.println("Hoy es " + hoy);
        
        // Obtener campos individualmente
        System.out.println("El año es " + hoy.getYear());
        System.out.println("El mes es " + hoy.getMonthValue());
        System.out.println("El dia es " + hoy.getDayOfMonth());
        
        // Creación de fechas concretas
        LocalDate nacimientoJose = LocalDate.of(1999, 3, 8);
        System.out.println("Jose es un mentiroso " + nacimientoJose);
        
        LocalDate nacimientoRosa = LocalDate.of(1988, Month.APRIL, 13);
        System.out.println("Rosa también miente " + nacimientoRosa);
        
        /* COMPARACIÓN DE FECHAS */
        System.out.println("Han nacido el mismo día? " + 
                nacimientoRosa.equals(nacimientoJose));
        
        if (nacimientoJose.isAfter(nacimientoRosa)){
            System.out.println("Jose es más joven...o eso dice");
        }
        
        // Método isBefore()
        
        /* EJEMPLO CON LA CLASE MONTHDAY */
        // Sólo guarda el día y el mes (no el año)
        LocalDate diaAnda = LocalDate.of(1976, Month.FEBRUARY, 28);
        
        MonthDay diaMesAndalucia = MonthDay.of(diaAnda.getMonth(), diaAnda.getDayOfMonth());
        MonthDay diaMesHoy = MonthDay.from(LocalDate.now());
        
        if (diaMesAndalucia.equals(diaMesHoy)){
            System.out.println("Hoy es el día de Andalucía");
            
        }
         
        /* EJEMPLO CON LA CLASE YEARMONTH */
        // Sólo guarda el año y el mes (no el día)
        
        YearMonth caducidadTarjeta = YearMonth.of(2019, Month.MARCH);
        System.out.println("Su tarjeta caduca en " + caducidadTarjeta);
        YearMonth actual = YearMonth.from(LocalDate.now());
        
        if (caducidadTarjeta.isBefore(actual)){
            System.out.println("Su tarjeta ha caducado");
        }
        
        /*OPERACIONES CON FECHAS. SUMA, RESTA DE DIAS, SEMANAS, AÑOS, ETC*/
        
        // Sumar semanas
        LocalDate semanaSiguiente = hoy.plus(1, ChronoUnit.WEEKS);
        System.out.println("Dentro de una semana " + semanaSiguiente);
        // Equivale a semanaSiguiente = hoy.plusWeeks(1);
        
        LocalDate suma18dias = hoy.plusDays(18);
        System.out.println("Dentro de 18 días " + suma18dias);
        
        // Restar un siglo
        LocalDate haceUnSiglo = hoy.minus(1, ChronoUnit.CENTURIES);
        System.out.println("Hace un siglo " + haceUnSiglo);
        System.out.println("¿Qué día de la semana? " + haceUnSiglo.getDayOfWeek());
        
        /* DÍAS ENTRE FECHAS */
        
        long diferenciaDias = ChronoUnit.DAYS.between(haceUnSiglo, hoy);
        System.out.println("Días entre fechas " + diferenciaDias);
        
        LocalDate finalCurso = LocalDate.of(2019,Month.JUNE, 25);
        long diasHastaFinal = ChronoUnit.DAYS.between(hoy, finalCurso);
        System.out.println("Días para terminar " + diasHastaFinal);
        
        long semanasHastaFinal = ChronoUnit.WEEKS.between(hoy, finalCurso);
        System.out.println("Semanas hasta el final " + semanasHastaFinal);
        
        /* AÑO BISIESTO */
        if (hoy.isLeapYear()){
            System.out.println("Este año es bisiesto");
        }
        
        /* Número de días del mes */
        System.out.println("Días del mes actual " + hoy.lengthOfMonth());
        
        LocalDate bisiesto = LocalDate.of(2000, 2, 11);
        System.out.println("Bisiesto? " + bisiesto.isLeapYear());
        
        System.out.println("Febrero del año 2000: " + bisiesto.lengthOfMonth());
        System.out.println("Días del año 2000: " + bisiesto.lengthOfYear());
        System.out.println("Días transcurridos en el año: " + bisiesto.getDayOfYear());
        
        /* EJEMPLO CON PERIOD */
        
        LocalDate newton = LocalDate.of(1643, 1,4);
        LocalDate einstein = LocalDate.of(1879, Month.MARCH, 14);
        
        Period tiempoEntreGenios = Period.between(newton, einstein);
        System.out.println(" Tiempo entre nacimientos. Años: " + tiempoEntreGenios.getYears()
         + " Meses: " + tiempoEntreGenios.getMonths() + " Días: " + tiempoEntreGenios.getDays());
        System.out.println(tiempoEntreGenios);
        System.out.println("Meses totales entre fechas " + tiempoEntreGenios.toTotalMonths());
        
        /* MOSTRAR INFORMACIÓN */
        
        DayOfWeek lunes = DayOfWeek.MONDAY;
        
        // Guardo en objeto locale la información del sistema donde
        // está la máquina virtual
        System.out.println("Lunes " + lunes);
        Locale configSistema = Locale.getDefault();
        
        System.out.println("TextStyle.FULL " + lunes.getDisplayName(TextStyle.FULL, configSistema));
        System.out.println("TextStyle.NARROW " + lunes.getDisplayName(TextStyle.NARROW, configSistema));
        System.out.println("TextStyle.SHORT " + lunes.getDisplayName(TextStyle.SHORT, configSistema));
        
        Month mes = hoy.getMonth();
        DayOfWeek dia = hoy.getDayOfWeek();
        System.out.println("MES TextStyle.FULL " + mes.getDisplayName(TextStyle.FULL, configSistema));
        
        // La clase DateTimeFormatter formatea fechas y horas
        // para poder ser impresas en gran cantidad de formas
        // Más info en la API de Java
        
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd MM yyyy");
        String fechaString = hoy.format(formato);
        System.out.println("Fecha sin formato " + hoy);
        System.out.println("Fecha con formato " + fechaString);
        
        // Este objeto guarda fecha y hora
        LocalDateTime ya = LocalDateTime.now();
        DateTimeFormatter formato2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String fechaString2 = ya.format(formato2);
        System.out.println("Fecha sin formato " + ya);
        System.out.println("Fecha con formato " + fechaString2);
        
    }
    
}
