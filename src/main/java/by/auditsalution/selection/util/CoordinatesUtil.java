package by.auditsalution.selection.util;

import by.auditsalution.selection.model.Account;
import by.auditsalution.selection.model.Coordinates;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class CoordinatesUtil {

    private static final Map<Account, Coordinates> COORDINATES_MAP = new HashMap<>();

    static {
        COORDINATES_MAP.put(Account.A01, Coordinates.builder().positionRandom(33).positionMax(53).positionSaldoRandom(72).positionSaldoMax(92).build());
        COORDINATES_MAP.put(Account.A08, Coordinates.builder().positionRandom(118).positionMax(138).positionSaldoRandom(157).positionSaldoMax(177).build());
        COORDINATES_MAP.put(Account.A07, Coordinates.builder().positionRandom(202).positionMax(222).positionSaldoRandom(241).positionSaldoMax(261).build());
        COORDINATES_MAP.put(Account.A02, Coordinates.builder().positionRandom(286).positionMax(306).positionSaldoRandom(325).positionSaldoMax(345).build());
        COORDINATES_MAP.put(Account.A03, Coordinates.builder().positionRandom(370).positionMax(390).positionSaldoRandom(409).positionSaldoMax(429).build());
        COORDINATES_MAP.put(Account.A04, Coordinates.builder().positionRandom(454).positionMax(474).positionSaldoRandom(493).positionSaldoMax(513).build());
        COORDINATES_MAP.put(Account.A05, Coordinates.builder().positionRandom(538).positionMax(558).positionSaldoRandom(577).positionSaldoMax(597).build());
        COORDINATES_MAP.put(Account.A06, Coordinates.builder().positionRandom(622).positionMax(642).positionSaldoRandom(661).positionSaldoMax(681).build());
        COORDINATES_MAP.put(Account.A09, Coordinates.builder().positionRandom(706).positionMax(726).positionSaldoRandom(745).positionSaldoMax(765).build());
        COORDINATES_MAP.put(Account.A10, Coordinates.builder().positionRandom(790).positionMax(810).positionSaldoRandom(829).positionSaldoMax(849).build());
        COORDINATES_MAP.put(Account.A11, Coordinates.builder().positionRandom(874).positionMax(894).positionSaldoRandom(913).positionSaldoMax(933).build());
        COORDINATES_MAP.put(Account.A14, Coordinates.builder().positionRandom(958).positionMax(978).positionSaldoRandom(997).positionSaldoMax(1017).build());
        COORDINATES_MAP.put(Account.A15, Coordinates.builder().positionRandom(1042).positionMax(1062).positionSaldoRandom(1081).positionSaldoMax(1101).build());
        COORDINATES_MAP.put(Account.A16, Coordinates.builder().positionRandom(1126).positionMax(1146).positionSaldoRandom(1165).positionSaldoMax(1185).build());
        COORDINATES_MAP.put(Account.A18, Coordinates.builder().positionRandom(1210).positionMax(1230).positionSaldoRandom(1249).positionSaldoMax(1269).build());
        COORDINATES_MAP.put(Account.A20, Coordinates.builder().positionRandom(1294).positionMax(1314).positionSaldoRandom(1333).positionSaldoMax(1353).build());
        COORDINATES_MAP.put(Account.A21, Coordinates.builder().positionRandom(1378).positionMax(1398).positionSaldoRandom(1417).positionSaldoMax(1437).build());
        COORDINATES_MAP.put(Account.A23, Coordinates.builder().positionRandom(1462).positionMax(1482).positionSaldoRandom(1501).positionSaldoMax(1521).build());
        COORDINATES_MAP.put(Account.A25, Coordinates.builder().positionRandom(1546).positionMax(1566).positionSaldoRandom(1585).positionSaldoMax(1605).build());
        COORDINATES_MAP.put(Account.A26, Coordinates.builder().positionRandom(1630).positionMax(1650).positionSaldoRandom(1669).positionSaldoMax(1689).build());
        COORDINATES_MAP.put(Account.A28, Coordinates.builder().positionRandom(1714).positionMax(1734).positionSaldoRandom(1753).positionSaldoMax(1773).build());
        COORDINATES_MAP.put(Account.A29, Coordinates.builder().positionRandom(1798).positionMax(1818).positionSaldoRandom(1837).positionSaldoMax(1857).build());
        COORDINATES_MAP.put(Account.A41, Coordinates.builder().positionRandom(1882).positionMax(1902).positionSaldoRandom(1921).positionSaldoMax(1941).build());
        COORDINATES_MAP.put(Account.A42_1, Coordinates.builder().positionRandom(1966).positionMax(1986).positionSaldoRandom(2005).positionSaldoMax(2025).build());
        COORDINATES_MAP.put(Account.A42_3, Coordinates.builder().positionRandom(2050).positionMax(2070).positionSaldoRandom(2089).positionSaldoMax(2109).build());
        COORDINATES_MAP.put(Account.A43, Coordinates.builder().positionRandom(2134).positionMax(2154).positionSaldoRandom(2173).positionSaldoMax(2193).build());
        COORDINATES_MAP.put(Account.A44, Coordinates.builder().positionRandom(2218).positionMax(2238).positionSaldoRandom(2257).positionSaldoMax(2277).build());
        COORDINATES_MAP.put(Account.A47, Coordinates.builder().positionRandom(2302).positionMax(2322).positionSaldoRandom(2341).positionSaldoMax(2361).build());
        COORDINATES_MAP.put(Account.A50, Coordinates.builder().positionRandom(2386).positionMax(2406).positionSaldoRandom(2425).positionSaldoMax(2445).build());
        COORDINATES_MAP.put(Account.A51, Coordinates.builder().positionRandom(2470).positionMax(2490).positionSaldoRandom(2509).positionSaldoMax(2529).build());
        COORDINATES_MAP.put(Account.A52, Coordinates.builder().positionRandom(2554).positionMax(2574).positionSaldoRandom(2593).positionSaldoMax(2613).build());
        COORDINATES_MAP.put(Account.A55, Coordinates.builder().positionRandom(2638).positionMax(2658).positionSaldoRandom(2677).positionSaldoMax(2697).build());
        COORDINATES_MAP.put(Account.A57, Coordinates.builder().positionRandom(2722).positionMax(2742).positionSaldoRandom(2761).positionSaldoMax(2781).build());
        COORDINATES_MAP.put(Account.A58, Coordinates.builder().positionRandom(2806).positionMax(2826).positionSaldoRandom(2845).positionSaldoMax(2865).build());
        COORDINATES_MAP.put(Account.A60, Coordinates.builder().positionRandom(2890).positionMax(2910).positionSaldoRandom(2929).positionSaldoMax(2949).build());
        COORDINATES_MAP.put(Account.A62, Coordinates.builder().positionRandom(2974).positionMax(2994).positionSaldoRandom(3013).positionSaldoMax(3033).build());
        COORDINATES_MAP.put(Account.A63, Coordinates.builder().positionRandom(3058).positionMax(3078).positionSaldoRandom(3097).positionSaldoMax(3117).build());
        COORDINATES_MAP.put(Account.A65, Coordinates.builder().positionRandom(3142).positionMax(3162).positionSaldoRandom(3181).positionSaldoMax(3201).build());
        COORDINATES_MAP.put(Account.A66, Coordinates.builder().positionRandom(3226).positionMax(3246).positionSaldoRandom(3265).positionSaldoMax(3285).build());
        COORDINATES_MAP.put(Account.A67, Coordinates.builder().positionRandom(3310).positionMax(3330).positionSaldoRandom(3349).positionSaldoMax(3369).build());
        COORDINATES_MAP.put(Account.A68_1_1, Coordinates.builder().positionRandom(3394).positionMax(3414).positionSaldoRandom(3433).positionSaldoMax(3453).build());
        COORDINATES_MAP.put(Account.A68_2_1, Coordinates.builder().positionRandom(3478).positionMax(3498).positionSaldoRandom(3517).positionSaldoMax(3537).build());
        COORDINATES_MAP.put(Account.A68_3_1, Coordinates.builder().positionRandom(3562).positionMax(3582).positionSaldoRandom(3601).positionSaldoMax(3621).build());
        COORDINATES_MAP.put(Account.A68_3_2, Coordinates.builder().positionRandom(3646).positionMax(3666).positionSaldoRandom(3685).positionSaldoMax(3705).build());
        COORDINATES_MAP.put(Account.A68_3_3, Coordinates.builder().positionRandom(3730).positionMax(3750).positionSaldoRandom(3769).positionSaldoMax(3789).build());
        COORDINATES_MAP.put(Account.A68_3_4, Coordinates.builder().positionRandom(3814).positionMax(3834).positionSaldoRandom(3853).positionSaldoMax(3873).build());
        COORDINATES_MAP.put(Account.A68_3_5, Coordinates.builder().positionRandom(3898).positionMax(3918).positionSaldoRandom(3937).positionSaldoMax(3957).build());
        COORDINATES_MAP.put(Account.A68_4_1, Coordinates.builder().positionRandom(3982).positionMax(4002).positionSaldoRandom(4021).positionSaldoMax(4041).build());
        COORDINATES_MAP.put(Account.A68_5_1, Coordinates.builder().positionRandom(4066).positionMax(4086).positionSaldoRandom(4105).positionSaldoMax(4125).build());
        COORDINATES_MAP.put(Account.A68_5_2, Coordinates.builder().positionRandom(4150).positionMax(4170).positionSaldoRandom(4189).positionSaldoMax(4209).build());
        COORDINATES_MAP.put(Account.A68_5_3, Coordinates.builder().positionRandom(4234).positionMax(4254).positionSaldoRandom(4273).positionSaldoMax(4293).build());
        COORDINATES_MAP.put(Account.A68_5_4, Coordinates.builder().positionRandom(4318).positionMax(4338).positionSaldoRandom(4357).positionSaldoMax(4377).build());
        COORDINATES_MAP.put(Account.A69, Coordinates.builder().positionRandom(4402).positionMax(4422).positionSaldoRandom(4441).positionSaldoMax(4461).build());
        COORDINATES_MAP.put(Account.A70, Coordinates.builder().positionRandom(4486).positionMax(4506).positionSaldoRandom(4525).positionSaldoMax(4545).build());
        COORDINATES_MAP.put(Account.A71, Coordinates.builder().positionRandom(4570).positionMax(4590).positionSaldoRandom(4609).positionSaldoMax(4629).build());
        COORDINATES_MAP.put(Account.A73, Coordinates.builder().positionRandom(4654).positionMax(4674).positionSaldoRandom(4693).positionSaldoMax(4713).build());
        COORDINATES_MAP.put(Account.A75, Coordinates.builder().positionRandom(4738).positionMax(4758).positionSaldoRandom(4777).positionSaldoMax(4797).build());
        COORDINATES_MAP.put(Account.A76, Coordinates.builder().positionRandom(4822).positionMax(4842).positionSaldoRandom(4861).positionSaldoMax(4881).build());
        COORDINATES_MAP.put(Account.A79, Coordinates.builder().positionRandom(4906).positionMax(4926).positionSaldoRandom(4945).positionSaldoMax(4965).build());
        COORDINATES_MAP.put(Account.A80, Coordinates.builder().positionRandom(4990).positionMax(5010).positionSaldoRandom(5029).positionSaldoMax(5049).build());
        COORDINATES_MAP.put(Account.A82, Coordinates.builder().positionRandom(5074).positionMax(5094).positionSaldoRandom(5113).positionSaldoMax(5133).build());
        COORDINATES_MAP.put(Account.A83, Coordinates.builder().positionRandom(5158).positionMax(5178).positionSaldoRandom(5197).positionSaldoMax(5217).build());
        COORDINATES_MAP.put(Account.A84, Coordinates.builder().positionRandom(5242).positionMax(5262).positionSaldoRandom(5281).positionSaldoMax(5301).build());
        COORDINATES_MAP.put(Account.A86, Coordinates.builder().positionRandom(5326).positionMax(5346).positionSaldoRandom(5365).positionSaldoMax(5385).build());
        COORDINATES_MAP.put(Account.A96, Coordinates.builder().positionRandom(5410).positionMax(5430).positionSaldoRandom(5449).positionSaldoMax(5469).build());
        COORDINATES_MAP.put(Account.A97, Coordinates.builder().positionRandom(5494).positionMax(5514).positionSaldoRandom(5533).positionSaldoMax(5553).build());
        COORDINATES_MAP.put(Account.A98, Coordinates.builder().positionRandom(5578).positionMax(5598).positionSaldoRandom(5617).positionSaldoMax(5637).build());
        COORDINATES_MAP.put(Account.A94, Coordinates.builder().positionRandom(5662).positionMax(5682).positionSaldoRandom(5701).positionSaldoMax(5721).build());
        COORDINATES_MAP.put(Account.A90_1, Coordinates.builder().positionRandom(5746).positionMax(5766).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A90_2, Coordinates.builder().positionRandom(5791).positionMax(5811).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A90_4, Coordinates.builder().positionRandom(5836).positionMax(5856).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A90_5, Coordinates.builder().positionRandom(5881).positionMax(5901).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A90_6, Coordinates.builder().positionRandom(5926).positionMax(5946).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A90_7, Coordinates.builder().positionRandom(5971).positionMax(5991).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A90_8, Coordinates.builder().positionRandom(6016).positionMax(6036).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A90_10, Coordinates.builder().positionRandom(6061).positionMax(6081).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A91_1, Coordinates.builder().positionRandom(6106).positionMax(6126).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A91_2, Coordinates.builder().positionRandom(6151).positionMax(6171).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A91_3, Coordinates.builder().positionRandom(6196).positionMax(6216).positionSaldoRandom(0).positionSaldoMax(0).build());
        COORDINATES_MAP.put(Account.A91_4, Coordinates.builder().positionRandom(6241).positionMax(6261).positionSaldoRandom(0).positionSaldoMax(0).build());
    }

    public static Coordinates getCoordinates(Account account) {
        return COORDINATES_MAP.get(account);
    }

}
