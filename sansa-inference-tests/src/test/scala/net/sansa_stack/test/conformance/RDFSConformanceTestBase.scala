package net.sansa_stack.test.conformance

import java.io.File

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
  * The class is to test the conformance of each materialization rule of RDFS(simple) entailment.
  *
  * @author Lorenz Buehmann
  *
  */
@RunWith(classOf[JUnitRunner])
abstract class RDFSConformanceTestBase extends ConformanceTestBase {

  behavior of "conformance of RDFS(simple) entailment rules"

  override def testCaseIds: Set[String] = Set(
    "rdfbased-sem-rdfs-domain-cond",
    "rdfbased-sem-rdfs-range-cond",
    "rdfbased-sem-rdfs-subclass-cond",
    "rdfbased-sem-rdfs-subclass-trans",
    "rdfbased-sem-rdfs-subprop-cond",
    "rdfbased-sem-rdfs-subprop-trans")

  override def testsCasesFolder = new File(this.getClass.getClassLoader.getResource("data/conformance/rdfs").getPath)
}
