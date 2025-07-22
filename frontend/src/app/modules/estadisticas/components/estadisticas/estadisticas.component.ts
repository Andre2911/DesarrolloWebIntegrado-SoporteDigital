import { Component } from '@angular/core';
import {CommonModule} from '@angular/common';
import {ChartModule} from 'primeng/chart';
import {FluidModule} from 'primeng/fluid';
import {debounceTime, Subscription} from 'rxjs';
import {LayoutService} from '../../../../layouts/service/layout.service';

@Component({
  selector: 'app-estadisticas',
  imports: [CommonModule, ChartModule, FluidModule],
  templateUrl: './estadisticas.component.html',
  styleUrl: './estadisticas.component.css'
})
export class EstadisticasComponent {
  lineData: any;

  barData: any;

  pieData: any;

  polarData: any;

  radarData: any;

  lineOptions: any;

  barOptions: any;

  pieOptions: any;

  polarOptions: any;

  radarOptions: any;

  subscription: Subscription;
  constructor(private layoutService: LayoutService) {
    this.subscription = this.layoutService.configUpdate$.pipe(debounceTime(25)).subscribe(() => {
      this.initCharts();
    });
  }

  ngOnInit() {
    this.initCharts();
  }

  initCharts() {
    const documentStyle = getComputedStyle(document.documentElement);
    const textColor = documentStyle.getPropertyValue('--text-color');
    const textColorSecondary = documentStyle.getPropertyValue('--text-color-secondary');
    const surfaceBorder = documentStyle.getPropertyValue('--surface-border');

    this.lineData = {
      labels: ['Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio'],
      datasets: [
        {
          label: 'Solicitudes registradas',
          data: [1, 3, 2, 4, 2, 3, 1],
          fill: false,
          backgroundColor: '#2196f3',
          borderColor: '#2196f3',
          tension: 0.4
        }
      ]
    };

    this.lineOptions = {
      maintainAspectRatio: false,
      aspectRatio: 0.8,
      plugins: {
        legend: {
          labels: {
            color: textColor
          }
        }
      },
      scales: {
        x: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false
          }
        },
        y: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false
          }
        }
      }
    };

    this.barData = {
      labels: ['Error de software', 'Capacitación', 'Requerimiento'],
      datasets: [
        {
          label: 'Solicitudes por tipo',
          backgroundColor: '#2196f3',
          borderColor: '#2196f3',
          data: [5, 2, 1]
        }
      ]
    };

    this.barOptions = {
      maintainAspectRatio: false,
      aspectRatio: 0.8,
      plugins: {
        legend: {
          labels: {
            color: textColor
          }
        }
      },
      scales: {
        x: {
          ticks: {
            color: textColorSecondary,
            font: {
              weight: 500
            }
          },
          grid: {
            display: false,
            drawBorder: false
          }
        },
        y: {
          ticks: {
            color: textColorSecondary
          },
          grid: {
            color: surfaceBorder,
            drawBorder: false
          }
        }
      }
    };

    this.pieData = {
      labels: ['Juan', 'Ana', 'Carlos'],
      datasets: [
        {
          data: [3, 2, 1],
          backgroundColor: ['#3f51b5', '#9c27b0', '#009688'],
          hoverBackgroundColor: ['#5c6bc0', '#ba68c8', '#4db6ac']
        }
      ]
    };

    this.pieOptions = {
      plugins: {
        legend: {
          labels: {
            usePointStyle: true,
            color: textColor
          }
        }
      }
    };

    this.polarData = {
      labels: ['Problema técnico', 'Consulta general', 'Reinstalación', 'Integración'],
      datasets: [
        {
          data: [2, 1, 1, 1],
          backgroundColor: ['#3f51b5', '#9c27b0', '#009688', '#ff9800']
        }
      ]
    };

    this.polarOptions = {
      plugins: {
        legend: {
          labels: {
            color: textColor
          }
        }
      },
      scales: {
        r: {
          grid: {
            color: surfaceBorder,
          },
          ticks: {
            display: false,
            color: textColorSecondary
          }
        }
      }
    };

    this.radarData = {
      labels: ['Análisis', 'Diseño', 'Programación', 'Pruebas', 'Documentación', 'Soporte', 'Corrección'],
      datasets: [
        {
          label: 'Colaborador A',
          borderColor: '#7986cb',
          pointBackgroundColor: '#7986cb',
          pointBorderColor: '#7986cb',
          pointHoverBackgroundColor: textColor,
          pointHoverBorderColor: '#7986cb',
          data: [2, 1, 3, 2, 1, 1, 0]
        },
        {
          label: 'Colaborador B',
          borderColor: '#ce93d8',
          pointBackgroundColor: '#ce93d8',
          pointBorderColor: '#ce93d8',
          pointHoverBackgroundColor: textColor,
          pointHoverBorderColor: '#ce93d8',
          data: [1, 2, 1, 1, 0, 0, 1]
        }
      ]
    };

  }

  ngOnDestroy() {
    if (this.subscription) {
      this.subscription.unsubscribe();
    }
  }
}
