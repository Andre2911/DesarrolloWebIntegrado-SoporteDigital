import {Component, inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from '@angular/forms';
import {DropdownModule} from 'primeng/dropdown';
import {InputText} from 'primeng/inputtext';
import {Calendar} from 'primeng/calendar';
import {ButtonDirective} from 'primeng/button';
import {SolicitudesService} from '../../../../service/solicitudes.service';
import {ActivatedRoute, Router} from '@angular/router';


export interface IdLabel {
  id: number;
  label: string;
}


@Component({
  selector: 'app-agregar-solicitud',
  imports: [
    DropdownModule,
    InputText,
    ReactiveFormsModule,
    Calendar,
    ButtonDirective
  ],
  templateUrl: './agregar-solicitud.component.html',
  styleUrl: './agregar-solicitud.component.css'
})
export class AgregarSolicitudComponent implements OnInit{
  fb = inject(FormBuilder);
  form: FormGroup;
  service = inject(SolicitudesService);
  router = inject(Router);
  id = inject(ActivatedRoute).snapshot.paramMap.get('id');



  tipos: IdLabel[] = [];
  aplicativos: IdLabel[] = [];

  constructor() {
    this.form = this.fb.group({
      tipoSolicitudId: [null, Validators.required],
      aplicativoId: [null, Validators.required],
      descripcion: ['', Validators.required]
    });

    this.service.getTiposSolicitud().subscribe({
      next: (data: any) => {
        this.tipos = data.data.map((item: any) => ({ id: item.id, label: item.label }));
      },
      error: (err) => console.error('Error al cargar tipos de solicitud', err)
    });

    this.service.getAplicativos().subscribe({
      next: (data: any) => {
        this.aplicativos = data.data.map((item: any) => ({ id: item.id, label: item.label }));
      },
      error: (err) => console.error('Error al cargar aplicativos', err)
    });
  }

  ngOnInit() {
    if (this.id) {
      this.service.getById(+this.id).subscribe(res => {
        const solicitud = res.data;

        const tipoValue = this.tipos.find((tipo: any) => tipo.label === solicitud.tipo)?.id;
        const aplicativoValue = this.aplicativos.find((app: any) => app.label === solicitud.aplicativo)?.id;

        this.form.patchValue({
          tipoSolicitudId: tipoValue,
          aplicativoId: aplicativoValue,
          descripcion: solicitud.descripcion
        });
      });
    }
  }


  guardar() {
    if (this.form.invalid) {
      this.form.markAllAsTouched();
      return;
    }

    const solicitud = this.form.value;

    const obs = this.id
      ? this.service.update(+this.id, solicitud)
      : this.service.create(solicitud);

    obs.subscribe({
      next: () => this.router.navigate(['/dashboard/solicitudes']),
      error: (err) => console.error('Error al guardar solicitud', err)
    });
  }
}
