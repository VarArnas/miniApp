package com.example.miniapp.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "car_part")
public class CarPart {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(cascade = {CascadeType.PERSIST})
    @JoinTable(
            name = "car_part_car",
            joinColumns = @JoinColumn(name = "car_part_id",
                                        foreignKey = @ForeignKey(
                                                foreignKeyDefinition = "FOREIGN KEY (car_part_id) REFERENCES car_part(id) ON DELETE CASCADE"
                                        )),
            inverseJoinColumns = @JoinColumn(name = "car_id",
                                            foreignKey = @ForeignKey(
                                                    foreignKeyDefinition = "FOREIGN KEY (car_id) REFERENCES car(id) ON DELETE CASCADE"
                                            ))
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Car> cars = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mechanic_shop_id",
                nullable = false,
                foreignKey = @ForeignKey(
                        foreignKeyDefinition = "FOREIGN KEY (mechanic_shop_id) REFERENCES mechanic_shop(id) ON DELETE CASCADE"
                ))
    private MechanicShop mechanicShop;
}
